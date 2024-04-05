package com.andryu.kotlin.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.rxjava.adapter.RxjavaOptAdapter
import com.andryu.kotlin.rxjava.data.RxjavaEntity
import com.andryu.kotlin.rxjava.data.RxjavaOptType
import com.andryu.kotlin.rxjava.databinding.FragmentThirdRxjavaBinding

/**
 * Rxjava实现类
 */
class RxjavaFragment : BaseFragment() {

    private lateinit var binding: FragmentThirdRxjavaBinding
    private var mDataList:MutableList<RxjavaEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdRxjavaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.rvRxjava.layoutManager = LinearLayoutManager(context)
        val mAdapter = RxjavaOptAdapter(mDataList)
        binding.rvRxjava.adapter = mAdapter
        binding.rvRxjava.isNestedScrollingEnabled = false
    }

    private fun initData(){
        mDataList.clear()
        mDataList.add(RxjavaEntity("创建操作", RxjavaOptType.CREATE))
        mDataList.add(RxjavaEntity("变换操作", RxjavaOptType.TRANSFER))
        mDataList.add(RxjavaEntity("过滤操作", RxjavaOptType.FILTER))
        mDataList.add(RxjavaEntity("结合操作", RxjavaOptType.COMBINE))
        mDataList.add(RxjavaEntity("错误处理", RxjavaOptType.ERROR))
        mDataList.add(RxjavaEntity("辅助操作", RxjavaOptType.AUXILIARY))
        mDataList.add(RxjavaEntity("条件和布尔操作", RxjavaOptType.CONDITIONAL))
        mDataList.add(RxjavaEntity("算术和聚合操作", RxjavaOptType.ARITHMETIC))
        mDataList.add(RxjavaEntity("连接操作", RxjavaOptType.CONNECT))
    }
}