package com.andryu.kotlin.rxjava.data

/**
 * 操作符类型
 */
class RxjavaOptType {
    companion object {
        const val CREATE = 1
        const val TRANSFER = 2
        const val FILTER = 3
        const val COMBINE = 4
        const val ERROR = 5
        const val AUXILIARY = 6
        const val CONDITIONAL = 7
        const val ARITHMETIC = 8
        const val CONNECT = 9
    }
}