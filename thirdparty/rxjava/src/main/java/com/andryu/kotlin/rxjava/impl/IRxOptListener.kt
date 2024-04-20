package com.andryu.kotlin.rxjava.impl

import com.andryu.kotlin.rxjava.data.RxjavaOptEntity

interface IRxOptListener {

    fun getList():MutableList<RxjavaOptEntity>

    fun doOpt(type:String, callBack:(value:String) -> Unit)

    fun doSingleOpt(type: String, callBack:(value:String) -> Unit)
}