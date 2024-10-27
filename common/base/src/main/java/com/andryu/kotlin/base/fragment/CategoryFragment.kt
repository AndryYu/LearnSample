package com.andryu.kotlin.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.LogUtils
import com.andryu.kotlin.base.R
import com.andryu.kotlin.base.adapter.CategoryAdapter
import com.andryu.kotlin.base.databinding.FragmentCategoryBinding
import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.base.listener.IFragmentListener

/**
 * 抽象父类
 * 子类需要实现
 * 1. initData初始化数据
 */
abstract class CategoryFragment : BaseFragment(), IFragmentListener {
    protected val TAG: String = "CategoryFragment"
    private var _binding: FragmentCategoryBinding? = null
    protected val binding get() = _binding!!
    protected var mDataList = mutableListOf<LearnEntity>()
    protected var mAdapter: CategoryAdapter? = null
    protected var mCurrentFragment: BaseFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.rvContentParty.layoutManager = LinearLayoutManager(context)
        mAdapter = CategoryAdapter(mDataList)
        binding.rvContentParty.adapter = mAdapter
        mAdapter?.setOnItemClick {
            onFragmentClick(it.fragment)
        }
        binding.tvCategoryBack.setOnClickListener {
            val backCount = childFragmentManager.backStackEntryCount
            LogUtils.d(TAG, "backStackEntryCount: $backCount")
            if (backCount > 1) {
                childFragmentManager.popBackStack()
            } else {
                binding.tvCategoryBack.visibility = View.GONE
                binding.rvContentParty.visibility = View.VISIBLE
                binding.flSubContent.visibility = View.GONE
            }
        }
    }

    abstract fun initData()

    override fun onFragmentClick(fragment: BaseFragment) {
        if (!binding.tvCategoryBack.isVisible) {
            binding.tvCategoryBack.visibility = View.VISIBLE
        }
        if (binding.rvContentParty.isVisible) {
            binding.rvContentParty.visibility = View.GONE
        }
        if (!binding.flSubContent.isVisible) {
            binding.flSubContent.visibility = View.VISIBLE
        }
        mCurrentFragment = fragment;
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_sub_content, fragment)
            .addToBackStack(fragment::class.java.name)
            .commitAllowingStateLoss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}