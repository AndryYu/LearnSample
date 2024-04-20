package com.andryu.kotlin.rxjava.impl

import com.andryu.kotlin.rxjava.data.RxjavaOptEntity

class RxCombineImpl:IRxOptListener {
    override fun getList(): MutableList<RxjavaOptEntity> {
        TODO("Not yet implemented")
    }

    override fun doOpt(type: String, callBack: (value: String) -> Unit) {

    }

    override fun doSingleOpt(type: String, callBack: (value: String) -> Unit) {

    }
}