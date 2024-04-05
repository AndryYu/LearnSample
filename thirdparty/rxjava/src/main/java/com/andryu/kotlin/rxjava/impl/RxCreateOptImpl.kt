package com.andryu.kotlin.rxjava.impl

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.functions.Consumer

/**
 * 创建性操作符
 */
class RxCreateOptImpl : IRxOptListener {

    private var mHashMap: HashMap<String, Observable<String>> = hashMapOf()
    private var mSingleHashMap: HashMap<String, Single<String>> = hashMapOf()
    private var mType: String = ""
    private val result = ObservableOnSubscribe<String> { emitter ->
        emitter.onNext("Create")
        emitter.onNext("Option")
        emitter.onNext(":")
        emitter.onNext(mType)
    }
    private val singleResult = SingleOnSubscribe<String> {
        it.onSuccess("Single:$mType")
    }

    init {
        mHashMap["create"] = Observable.create(result)

        mSingleHashMap["create"] = Single.create(singleResult)
    }

    @SuppressLint("CheckResult")
    override fun doOpt(type: String, callBack: (value: String) -> Unit) {
        val result = StringBuffer()
        mHashMap[type]?.subscribe({
            result.append(it)
        }, {
            callBack("throwable: $result")
        }) {
            callBack(result.toString())
        }
    }

    @SuppressLint("CheckResult")
    override fun doSingleOpt(type: String, callBack: (value: String) -> Unit) {
        mSingleHashMap[type]?.subscribe(Consumer {
            callBack(it)
        })
    }
}