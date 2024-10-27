package com.andryyu.kotlin.self

import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.base.fragment.CategoryFragment
import com.andryu.kotlin.jetpack.JetpackCoroutineFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * 系统自带 目录fragment
 */
@AndroidEntryPoint
class SelfContainFragment : CategoryFragment() {


    override fun initData() {
        mDataList.clear()
        mDataList.add(LearnEntity("Kotlin协程", JetpackCoroutineFragment()))
    }
}