package com.andryu.kotlin.base;

import android.util.Log;

public class LogUtils {

    private static final String TAG = "LearnSample";
    public static void d(String Tag, String msg){
        Log.d(TAG, Tag+"#" + msg);
    }
}
