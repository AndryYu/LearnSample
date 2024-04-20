package com.andryu.kotlin.rxjava

import com.andryu.kotlin.rxjava.data.RxjavaOptType
import com.andryu.kotlin.rxjava.impl.IRxOptListener
import com.andryu.kotlin.rxjava.impl.RxArithmeticOptImpl
import com.andryu.kotlin.rxjava.impl.RxAuxiliaryOptImpl
import com.andryu.kotlin.rxjava.impl.RxCombineImpl
import com.andryu.kotlin.rxjava.impl.RxConditionalOptImpl
import com.andryu.kotlin.rxjava.impl.RxConnectOptImpl
import com.andryu.kotlin.rxjava.impl.RxCreateOptImpl
import com.andryu.kotlin.rxjava.impl.RxErrorOptImpl
import com.andryu.kotlin.rxjava.impl.RxFilterOptImpl
import com.andryu.kotlin.rxjava.impl.RxTransferOptImpl

class RxjavaHelper private constructor(){

    private object Holder{
        val mInstance = RxjavaHelper()
    }

    companion object{
        val instance = Holder.mInstance
    }

    /**
     * 获取相应操作符类型实现类
     */
    fun getOptImpl(type: Int): IRxOptListener {
        val optImpl: IRxOptListener = when(type){
            RxjavaOptType.CREATE -> RxCreateOptImpl()
            RxjavaOptType.TRANSFER -> RxTransferOptImpl()
            RxjavaOptType.FILTER -> RxFilterOptImpl()
            RxjavaOptType.COMBINE -> RxCombineImpl()
            RxjavaOptType.ERROR -> RxErrorOptImpl()
            RxjavaOptType.ARITHMETIC -> RxArithmeticOptImpl()
            RxjavaOptType.AUXILIARY -> RxAuxiliaryOptImpl()
            RxjavaOptType.CONDITIONAL -> RxConditionalOptImpl()
            RxjavaOptType.CONNECT -> RxConnectOptImpl()
            else -> {RxCreateOptImpl()}
        }
        return optImpl
    }
}