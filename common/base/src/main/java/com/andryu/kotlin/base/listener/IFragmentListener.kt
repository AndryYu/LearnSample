package com.andryu.kotlin.base.listener

import com.andryu.kotlin.base.fragment.BaseFragment

/**
 * activity与fragment交互接口
 */
interface IFragmentListener {
    fun onFragmentClick(fragment:BaseFragment)
}