#include <jni.h>
#include <string>
#include <android/log.h>
#include <assert.h>
#include <pthread.h>

#define LOG_TAG "simple"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

typedef struct tick_context {
    JavaVM *javaVM;
    jclass mainActivityClz;
    jobject mainActivityObj;
    pthread_mutex_t lock;
    int done;
} TickContext;
TickContext g_ctx;

extern "C"
JNIEXPORT
jstring JNICALL
Java_com_andryu_kotlin_jni_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    LOGI("Android stringFromJNI - %s", hello.c_str());
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    memset(&g_ctx, 0, sizeof(g_ctx));

    g_ctx.javaVM = vm;
    if (vm->GetEnv( (void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;  // JNI version not supported.
    }

    g_ctx.done = 0;
    g_ctx.mainActivityObj = NULL;
    return JNI_VERSION_1_6;
}

void sendJavaMsg(JNIEnv *env, jobject instance, jmethodID func, const char *msg) {
    jstring javaMsg = env->NewStringUTF(msg);
    env->CallVoidMethod(instance, func, javaMsg);
    env->DeleteLocalRef(javaMsg);
}

void *UpdateTicks(void *context) {
    TickContext *pctx = (TickContext *) context;
    JavaVM *javaVM = pctx->javaVM;
    JNIEnv *env;
    jint res = javaVM->GetEnv((void **) &env, JNI_VERSION_1_6);
    if (res != JNI_OK) {
        res = javaVM->AttachCurrentThread(&env, NULL);
        if (JNI_OK != res) {
            LOGE("Failed to AttachCurrentThread, ErrorCode = %d", res);
            return NULL;
        }
    }
    jmethodID statusId = env->GetMethodID(
             pctx->mainActivityClz, "updateStatus", "(Ljava/lang/String;)V");
    sendJavaMsg(env, pctx->mainActivityObj, statusId,
                "TickerThread status: initializing...");

    // get mainActivity updateTimer function
    jmethodID timerId =
            env->GetMethodID(pctx->mainActivityClz, "updateTicks", "()V");

    struct timeval beginTime, curTime, usedTime, leftTime;
    const struct timeval kOneSecond = {(__kernel_time_t) 1,
                                       (__kernel_suseconds_t) 0};

    sendJavaMsg(env, pctx->mainActivityObj, statusId,
                "TickerThread status: start ticking ...");
    while (1) {
        gettimeofday(&beginTime, NULL);
        pthread_mutex_lock(&pctx->lock);
        int done = pctx->done;
        if (pctx->done) {
            pctx->done = 0;
        }
        pthread_mutex_unlock(&pctx->lock);
        if (done) {
            break;
        }
        env->CallVoidMethod(pctx->mainActivityObj, timerId);

        gettimeofday(&curTime, NULL);
        timersub(&curTime, &beginTime, &usedTime);
        timersub(&kOneSecond, &usedTime, &leftTime);
        struct timespec sleepTime;
        sleepTime.tv_sec = leftTime.tv_sec;
        sleepTime.tv_nsec = leftTime.tv_usec * 1000;

        if (sleepTime.tv_sec <= 1) {
            nanosleep(&sleepTime, NULL);
        } else {
            sendJavaMsg(env, pctx->mainActivityObj, statusId,
                        "TickerThread error: processing too long!");
        }
    }

    sendJavaMsg(env, pctx->mainActivityObj, statusId,
                "TickerThread status: ticking stopped");
    javaVM->DetachCurrentThread();
    return context;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_andryu_kotlin_jni_NativeLib_startTicks(JNIEnv *env, jobject native,jobject instance) {
    pthread_t threadInfo_;
    pthread_attr_t threadAttr_;

    pthread_attr_init(&threadAttr_);
    pthread_attr_setdetachstate(&threadAttr_, PTHREAD_CREATE_DETACHED);

    pthread_mutex_init(&g_ctx.lock, NULL);

    jclass clz = env->GetObjectClass(instance);
    g_ctx.mainActivityClz = static_cast<jclass>(env->NewGlobalRef(clz));
    g_ctx.mainActivityObj = env->NewGlobalRef(instance);

    int result = pthread_create(&threadInfo_, &threadAttr_, UpdateTicks, &g_ctx);
    assert(result == 0);

    pthread_attr_destroy(&threadAttr_);

    (void) result;
}




extern "C"
JNIEXPORT void JNICALL
Java_com_andryu_kotlin_jni_NativeLib_stopTicks(JNIEnv *env, jobject thiz) {
    pthread_mutex_lock(&g_ctx.lock);
    g_ctx.done = 1;
    pthread_mutex_unlock(&g_ctx.lock);

    // waiting for ticking thread to flip the done flag
    struct timespec sleepTime;
    memset(&sleepTime, 0, sizeof(sleepTime));
    sleepTime.tv_nsec = 100000000;
    while (g_ctx.done) {
        nanosleep(&sleepTime, NULL);
    }

    // release object we allocated from StartTicks() function
    env->DeleteGlobalRef(g_ctx.mainActivityClz);
    env->DeleteGlobalRef( g_ctx.mainActivityObj);
    g_ctx.mainActivityObj = NULL;
    g_ctx.mainActivityClz = NULL;

    pthread_mutex_destroy(&g_ctx.lock);
}