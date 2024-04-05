package com.andryu.kotlin.rxjava.impl

interface IRxOptListener {
    fun doOpt(type:String, callBack:(value:String) -> Unit)

    fun doSingleOpt(type: String, callBack:(value:String) -> Unit)
}