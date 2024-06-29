package com.andryu.kotlin.base

import android.util.Log

object LogUtils {
    private const val TAG = "LearnSample"
    public const val NDK_TAG = "MODULE-NDK"
    public const val SELF_TAG = "MODULE-SELF"
    public const val THIRD_TAG = "MODULE-THIRD"
    fun d(tag: String, msg: String) {
        Log.d(TAG, "$tag#$msg")
    }


}
