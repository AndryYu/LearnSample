package com.andryu.kotlin.rxjava.impl

import android.annotation.SuppressLint
import android.util.Log
import com.andryu.kotlin.rxjava.data.RxjavaOptEntity
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
        emitter.onComplete()
    }
    private val singleResult = SingleOnSubscribe<String> {
        it.onSuccess("Single:$mType")
    }

    init {
        mHashMap["create"] = Observable.create(result)

        mSingleHashMap["create"] = Single.create(singleResult)
    }

    override fun getList(): MutableList<RxjavaOptEntity> {
        val list = mutableListOf<RxjavaOptEntity>()
        list.add(RxjavaOptEntity("操作符Create(Single)", "create"))

        return list
    }

    @SuppressLint("CheckResult")
    override fun doOpt(type: String, callBack: (value: String) -> Unit) {
        val result = StringBuffer()
        mType = type

        mHashMap[type]?.subscribe({
            result.append(it)
            Log.d("Rxjava","RxCreateOptImpl doOpt:$it")
        }, {
            callBack("throwable: $result")
            Log.d("Rxjava","RxCreateOptImpl doOpt throwable: $result")
        }) {
            callBack(result.toString())
            Log.d("Rxjava","RxCreateOptImpl doOpt $result")
        }
    }

    @SuppressLint("CheckResult")
    override fun doSingleOpt(type: String, callBack: (value: String) -> Unit) {
        mSingleHashMap[type]?.subscribe(Consumer {
            callBack(it)
        })
    }
}