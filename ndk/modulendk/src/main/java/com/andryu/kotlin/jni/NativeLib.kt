package com.andryu.kotlin.jni

class NativeLib {

    /**
     * A native method that is implemented by the 'simple' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String


    companion object {
        /*
         * this is used to load the 'hello-jni' library on application
         * startup. The library has already been unpacked into
         * /data/data/com.example.hellojni/lib/libhello-jni.so
         * at the installation time by the package manager.
         */
        init {
            System.loadLibrary("simple")
        }
    }
}