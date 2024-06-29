package com.andryu.kotlin.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.LogUtils
import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.base.listener.IFragmentListener
import com.andryu.kotlin.rxjava.RxjavaCategoryFragment
import com.andryu.kotlin.third.databinding.FragmentThirdLibraryBinding

/**
 * 第三方库 目录fragment
 */
class ThirdPartyFragment : BaseFragment(),IFragmentListener {

    private val TAG:String = "ThirdPartyFragment"
    private var _binding: FragmentThirdLibraryBinding? = null
    private val binding get() = _binding!!
    private var mDataList = mutableListOf<LearnEntity>()
    private var mAdapter: ThirdPartyAdapter? = null
    private var mCurrentFragment:BaseFragment ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.rvThirdParty.layoutManager = LinearLayoutManager(context)
        mAdapter = ThirdPartyAdapter(mDataList)
        binding.rvThirdParty.adapter = mAdapter
        mAdapter?.setOnItemClick {
            onFragmentClick(it.fragment)
        }
        binding.tvThirdBack.setOnClickListener {
            val backCount = childFragmentManager.backStackEntryCount
            LogUtils.d(TAG, "backStackEntryCount: $backCount")
            if (backCount > 1) {
                childFragmentManager.popBackStack()
            } else {
                binding.tvThirdBack.visibility = View.GONE
                binding.rvThirdParty.visibility = View.VISIBLE
                binding.flThirdContent.visibility = View.GONE
            }
        }
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mDataList.clear()
        mDataList.add(LearnEntity("Rxjava", RxjavaCategoryFragment()))
    }

    override fun onFragmentClick(fragment: BaseFragment) {
        if (!binding.tvThirdBack.isVisible) {
            binding.tvThirdBack.visibility = View.VISIBLE
        }
        if (binding.rvThirdParty.isVisible) {
            binding.rvThirdParty.visibility = View.GONE
        }
        if (!binding.flThirdContent.isVisible) {
            binding.flThirdContent.visibility = View.VISIBLE
        }
        mCurrentFragment = fragment;
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_third_content, fragment)
            .addToBackStack(fragment::class.java.name)
            .commitAllowingStateLoss()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}