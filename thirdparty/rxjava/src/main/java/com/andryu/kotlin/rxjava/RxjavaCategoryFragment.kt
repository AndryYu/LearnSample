package com.andryu.kotlin.rxjava

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.rxjava.adapter.RxjavaTypeAdapter
import com.andryu.kotlin.rxjava.data.RxjavaTypeEntity
import com.andryu.kotlin.rxjava.data.RxjavaOptType
import com.andryu.kotlin.rxjava.databinding.FragmentRxjavaCategoryBinding

/**
 * Rxjava实现类
 */
class RxjavaCategoryFragment : BaseFragment() {

    private lateinit var binding: FragmentRxjavaCategoryBinding
    private var mDataList:MutableList<RxjavaTypeEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRxjavaCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.rvRxjava.layoutManager = LinearLayoutManager(context)
        val mAdapter = RxjavaTypeAdapter(mDataList)
        binding.rvRxjava.apply{
            adapter = mAdapter
            isNestedScrollingEnabled = false
        }
        mAdapter.setOnItemClick {
            Log.d("Rxjava", "onItemClick:" + it.name)
            binding.flRxOpt.visibility = View.VISIBLE
            val fragment = RxjavaContentFragment()
            val bundle = Bundle();
            bundle.putInt("rxjavaType", it.type)
            fragment.arguments = bundle
            childFragmentManager.beginTransaction().replace(R.id.fl_rx_opt, fragment).commitAllowingStateLoss();
        }
    }

    private fun initData(){
        mDataList.clear()
        mDataList.add(RxjavaTypeEntity("创建操作", RxjavaOptType.CREATE))
        mDataList.add(RxjavaTypeEntity("变换操作", RxjavaOptType.TRANSFER))
        mDataList.add(RxjavaTypeEntity("过滤操作", RxjavaOptType.FILTER))
        mDataList.add(RxjavaTypeEntity("结合操作", RxjavaOptType.COMBINE))
        mDataList.add(RxjavaTypeEntity("错误处理", RxjavaOptType.ERROR))
        mDataList.add(RxjavaTypeEntity("辅助操作", RxjavaOptType.AUXILIARY))
        mDataList.add(RxjavaTypeEntity("条件和布尔操作", RxjavaOptType.CONDITIONAL))
        mDataList.add(RxjavaTypeEntity("算术和聚合操作", RxjavaOptType.ARITHMETIC))
        mDataList.add(RxjavaTypeEntity("连接操作", RxjavaOptType.CONNECT))
    }


}