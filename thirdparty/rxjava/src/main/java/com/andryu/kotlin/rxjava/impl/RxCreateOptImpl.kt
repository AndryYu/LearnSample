package com.andryu.kotlin.rxjava.impl

import android.annotation.SuppressLint
import android.util.Log
import com.andryu.kotlin.rxjava.data.RxjavaOptEntity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 创建性操作符
 */
class RxCreateOptImpl : IRxOptListener {

    private var mHashMap: HashMap<String, Observable<String>> = hashMapOf()
    private var mSingleHashMap: HashMap<String, Single<String>> = hashMapOf()
    private var mType: String = ""
    private var mDisposable: Disposable? = null
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
    private val mDataString = arrayOf("Create", " ", "Option", ":", "From")

    init {
        mHashMap["create"] = Observable.create(result)
        mHashMap["defer"] = Observable.defer {
            Observable.create(result)
        }
        mHashMap["throw"] = Observable.defer {
            Observable.error(Throwable("Throw是创建一个不发射数据以一个错误终止的Observable"))
        }
        mHashMap["from"] = Observable.fromArray(*mDataString)
        mHashMap["interval"] = Observable.interval(5, TimeUnit.SECONDS)
            .map { t ->
                val result = "interval $t"
                Log.d("Rxjava", "RxCreateOptImpl interval map $result")
                result
            }
        mHashMap["just"] = Observable.just("Create Option: ", "Just")
        mHashMap["range"] = Observable.range(1, 1).map { t -> "range $t" }
        mHashMap["repeat"] = Observable.just("Repeat").repeat(1)
        mHashMap["start"] = Observable.defer { Observable.just("start") }
            .startWith("option:")
        mHashMap["timer"] = Observable.timer(2, TimeUnit.SECONDS).map { along -> "Timer $along" }


        mSingleHashMap["create"] = Single.create(singleResult)
        mSingleHashMap["defer"] = Single.defer {
            Single.create(singleResult)
        }
        mSingleHashMap["throw"] = Single.defer {
            Single.error(Throwable("Throw是创建一个不发射数据以一个错误终止的Observable"))
        }
        mSingleHashMap["just"] = Single.just("Create Option（Single）: Just")
        mSingleHashMap["timer"] =
            Single.timer(2, TimeUnit.SECONDS).map { along -> "Timer(Single) $along" }
    }

    override fun getList(): MutableList<RxjavaOptEntity> {
        val list = mutableListOf<RxjavaOptEntity>()
        list.add(
            RxjavaOptEntity(
                "1.操作符Create(Single) — 通过调用观察者的方法从头创建一个Observable",
                "create"
            )
        )
        list.add(
            RxjavaOptEntity(
                "2.操作符Defer(Single) — 在观察者订阅之前不创建这个Observable，为每一个观察者创建一个新的Observable",
                "defer"
            )
        )
        list.add(
            RxjavaOptEntity(
                "3.操作符Empty/Never/Throw — 创建行为受限的特殊Observable",
                "throw"
            )
        )
        list.add(RxjavaOptEntity("4.操作符From — 将其它的对象或数据结构转换为Observable", "from"))
        list.add(
            RxjavaOptEntity(
                "5.操作符Interval — 创建一个定时发射整数序列的Observable",
                "interval"
            )
        )
        list.add(
            RxjavaOptEntity(
                "6.操作符Just — 将对象或者对象集合转换为一个会发射这些对象的Observable",
                "just"
            )
        )
        list.add(RxjavaOptEntity("7.操作符Range — 创建发射指定范围的整数序列的Observable", "range"))
        list.add(
            RxjavaOptEntity(
                "8.操作符Repeat — 创建重复发射特定的数据或数据序列的Observable",
                "repeat"
            )
        )
        list.add(RxjavaOptEntity("9.操作符Start — 创建发射一个函数的返回值的Observable", "start"))
        list.add(
            RxjavaOptEntity(
                "10.操作符Timer — 创建在一个指定的延迟之后发射单个数据的Observable",
                "timer"
            )
        )
        return list
    }

    @SuppressLint("CheckResult")
    override fun doOpt(type: String, callBack: (value: String) -> Unit) {
        if (!isOptIntecept(type, callBack)) {
            Log.d("Rxjava", "RxCreateOptImpl type: $type")
            mType = type
            val result = StringBuffer()
            mDisposable = mHashMap[type]?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (isTimerOpt(type)) {
                        Log.d("Rxjava", "RxCreateOptImpl doOpt $it")
                        callBack(it)
                    } else {
                        result.append(it)
                    }
                }, {
                    callBack("throwable: ${it.message}")
                    Log.d("Rxjava", "RxCreateOptImpl doOpt throwable: ${it.message}")
                }) {
                    mDisposable?.dispose()
                    callBack(result.toString())
                    Log.d("Rxjava", "RxCreateOptImpl doOpt $result")
                }
        }
    }

    @SuppressLint("CheckResult")
    override fun doSingleOpt(type: String, callBack: (value: String) -> Unit) {
        if (!isOptIntecept(type, callBack)) {
            mSingleHashMap[type]?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(Consumer {
                    callBack(it)
                }) {
                    it.message?.let { it1 -> callBack("throwable:$it1") }
                }
        }
    }

    /**
     * 处理特殊操作符
     */
    fun isOptIntecept(type: String, callBack: (value: String) -> Unit): Boolean {
        return false
    }

    private fun isTimerOpt(type: String): Boolean {
        if (type == "interval" || type == "repeat") {
            mDisposable?.dispose()
            return true
        }

        return false
    }
}
