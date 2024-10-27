package com.andryu.kotlin.jni

import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.base.fragment.CategoryFragment

class NdkFragment : CategoryFragment() {

    override fun initData() {
        mDataList.clear()
        mDataList.add(LearnEntity("JNI-Simple 使用", JNISimpleFragment()))
    }
}