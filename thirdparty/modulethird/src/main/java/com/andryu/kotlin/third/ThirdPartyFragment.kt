package com.andryu.kotlin.third

import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.base.fragment.CategoryFragment
import com.andryu.kotlin.rxjava.RxjavaCategoryFragment
import dagger.hilt.android.AndroidEntryPoint
/**
 * 第三方库 目录fragment
 */
@AndroidEntryPoint
class ThirdPartyFragment : CategoryFragment() {

    /**
     * 初始化数据
     */
    override fun initData() {
        mDataList.clear()
        mDataList.add(LearnEntity("Rxjava", RxjavaCategoryFragment()))
    }

}