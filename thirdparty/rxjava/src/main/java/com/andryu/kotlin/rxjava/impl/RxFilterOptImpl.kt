package com.andryu.kotlin.rxjava.impl

import com.andryu.kotlin.rxjava.data.RxjavaOptEntity

class RxFilterOptImpl:IRxOptListener {


    override fun getList(): MutableList<RxjavaOptEntity> {
        val list = mutableListOf<RxjavaOptEntity>()
        list.add(
            RxjavaOptEntity(
                "1.操作符filter — 过滤数据",
                "filter"
            )
        )
        list.add(
            RxjavaOptEntity(
                "2.操作符takeLast — 只发射最后的N项数据",
                "takeLast"
            )
        )
        list.add(
            RxjavaOptEntity(
                "3.操作符last — 只发射最后的一项数据",
                "last"
            )
        )
        list.add(
            RxjavaOptEntity(
                "4.操作符lastOrDefault — 只发射最后的一项数据，如果Observable为空就发射默认值",
                "lastOrDefault"
            )
        )
        list.add(
            RxjavaOptEntity(
                "5.操作符takeLastBuffer — 将最后的N项数据当做单个数据发射",
                "takeLastBuffer"
            )
        )
        list.add(
            RxjavaOptEntity(
                "6.操作符skip — 跳过开始的N项数据",
                "skip"
            )
        )
        list.add(
            RxjavaOptEntity(
                "7.操作符skipLast — 跳过最后的N项数据",
                "skipLast"
            )
        )
        list.add(
            RxjavaOptEntity(
                "8.操作符take — 只发射开始的N项数据",
                "take"
            )
        )
        list.add(
            RxjavaOptEntity(
                "9.操作符first、takeFirst — 只发射第一项数据，或者满足某种条件的第一项数据",
                "first"
            )
        )
        list.add(
            RxjavaOptEntity(
                "10.操作符firstOrDefault — 只发射第一项数据，如果Observable为空就发射默认值",
                "firstOrDefault"
            )
        )
        list.add(
            RxjavaOptEntity(
                "11.操作符elementAt — 发射第N项数据",
                "elementAt"
            )
        )
        list.add(
            RxjavaOptEntity(
                "12.操作符elementAtOrDefault — 发射第N项数据，如果Observable数据少于N项就发射默认值",
                "elementAtOrDefault"
            )
        )
        list.add(
            RxjavaOptEntity(
                "13.操作符sample、throttleLast — 定期发射Observable最近的数据",
                "sample"
            )
        )
        list.add(
            RxjavaOptEntity(
                "14.操作符throttleFirst — 定期发射Observable发射的第一项数据",
                "throttleFirst"
            )
        )
        list.add(
            RxjavaOptEntity(
                "15.操作符throttleWithTimeout、debounce — 只有当Observable在指定的时间后还没有发射数据时，才发射一个数据",
                "debounce"
            )
        )
        list.add(
            RxjavaOptEntity(
                "16.操作符timeout — 如果在一个指定的时间段后还没发射数据，就发射一个异常",
                "timeout"
            )
        )
        list.add(
            RxjavaOptEntity(
                "17.操作符distinct — 过滤掉重复数据",
                "distinct"
            )
        )
        list.add(
            RxjavaOptEntity(
                "18.操作符distinctUntilChanged — 过滤掉连续重复的数据",
                "distinctUntilChanged"
            )
        )
        list.add(
                RxjavaOptEntity(
                    "17.操作符ofType — 只发射指定类型的数据",
                    "ofType"
                )
                )
        list.add(
            RxjavaOptEntity(
                "18.操作符ignoreElements — 丢弃所有的正常数据，只发射错误或完成通知",
                "ignoreElements"
            )
        )
        return list
    }

    override fun doOpt(type: String, callBack: (value: String) -> Unit) {

    }

    override fun doSingleOpt(type: String, callBack: (value: String) -> Unit) {

    }
}