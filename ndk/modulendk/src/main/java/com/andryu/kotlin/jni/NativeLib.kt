package com.andryu.kotlin.jni

object NativeLib {

    /**
     * A native method that is implemented by the 'simple' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun startTicks(fragment: JNISimpleFragment)

    external fun stopTicks(fragment: JNISimpleFragment)
}