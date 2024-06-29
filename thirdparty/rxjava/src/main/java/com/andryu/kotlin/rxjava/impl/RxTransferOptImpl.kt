package com.andryu.kotlin.rxjava.impl

import android.util.Log
import com.andryu.kotlin.rxjava.data.RxjavaOptEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.Locale

class RxTransferOptImpl:IRxOptListener {
    private val mDataString = arrayOf("This", "is", "Rxjava")
    private var mType: String = ""
    private var mDisposable: Disposable? = null

    private var mHashMap: HashMap<String, Observable<String>> = hashMapOf()

    init {
        mHashMap["map"] = Observable.fromArray(*mDataString).map{ it.uppercase(Locale.ROOT) }
    }

    override fun getList(): MutableList<RxjavaOptEntity> {
        val list = mutableListOf<RxjavaOptEntity>()
        list.add(
            RxjavaOptEntity(
                "1.操作符map — 对序列的每一项都应用一个函数来变换Observable发射的数据序列",
                "map"
            )
        )
        list.add(
            RxjavaOptEntity(
                "2.操作符flatMap、concatMap、flatMapInterable — 将Observable发射的数据集合变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable",
                "flatMap"
            )
        )
        list.add(
            RxjavaOptEntity(
                "3.操作符switchMap — 将Observable发射的数据集合变换为Observables集合，然后只发射这些Observables最近发射的数据",
                "switchMap"
            )
        )
        list.add(
            RxjavaOptEntity(
                "4.操作符scan — 对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值",
                "scan"
            )
        )
        list.add(
            RxjavaOptEntity(
                "5.操作符groupBy — 将Observable分拆为Observable集合，将原始Observable发射色数据按Key分组，每一个Observable发射一组不同的数据",
                "groupBy"
            )
        )
        list.add(
            RxjavaOptEntity(
                "6.操作符buffer — 定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个",
                "buffer"
            )
        )
        list.add(
            RxjavaOptEntity(
                "7.操作符window — 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项",
                "window"
            )
        )
        list.add(
            RxjavaOptEntity(
                "8.操作符cast — 在发射之前强制将Observable发射的所有数据转换为指定类型",
                "cast"
            )
        )
        return list
    }

    override fun doOpt(type: String, callBack: (value: String) -> Unit) {
        Log.d("Rxjava", "RxCreateOptImpl type: $type")
        mType = type
        val result = StringBuffer()
        mDisposable = mHashMap[type]?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                result.append("$it ")
            }, {
                callBack("throwable: ${it.message}")
                Log.d("Rxjava", "RxTransferOptImpl doOpt throwable: ${it.message}")
            }) {
                mDisposable?.dispose()
                callBack(result.toString())
                Log.d("Rxjava", "RxTransferOptImpl doOpt $result")
            }
    }

    override fun doSingleOpt(type: String, callBack: (value: String) -> Unit) {

    }
}