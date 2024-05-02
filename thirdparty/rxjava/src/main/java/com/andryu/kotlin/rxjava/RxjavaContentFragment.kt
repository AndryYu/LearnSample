package com.andryu.kotlin.rxjava

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.rxjava.adapter.RxjavaOptAdapter
import com.andryu.kotlin.rxjava.data.RxjavaOptType
import com.andryu.kotlin.rxjava.databinding.FragmentRxjavaContentBinding
import com.andryu.kotlin.rxjava.impl.IRxOptListener

class RxjavaContentFragment:BaseFragment() {

    private lateinit var binding: FragmentRxjavaContentBinding
    private var mRxjavaImpl:IRxOptListener? = null
    private var mFlag  = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRxjavaContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = arguments?.getInt("rxjavaType", RxjavaOptType.CREATE)
        mRxjavaImpl = type?.let {
            RxjavaHelper.instance.getOptImpl(it)
        }
        val mAdapter = mRxjavaImpl?.getList()?.let { RxjavaOptAdapter(requireContext(), it) }

        binding.rvRxjavaOpt.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        mAdapter?.setOnItemClick { item ->
            Log.d("Rxjava", "RxjavaContentFragment onItemClick:" + item.name)
            if (mFlag % 2 == 0) {
                mRxjavaImpl?.doOpt(item.keyword, callBack = {
                    Toast.makeText(requireContext(), "执行结果:$it", Toast.LENGTH_SHORT).show()
                })
            } else {
                mRxjavaImpl?.doSingleOpt(item.keyword, callBack = {
                    Toast.makeText(requireContext(), "执行结果:$it", Toast.LENGTH_SHORT).show()
                })
            }
            mFlag += 1
        }
    }


}