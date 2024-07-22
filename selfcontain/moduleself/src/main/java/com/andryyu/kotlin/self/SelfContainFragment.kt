package com.andryyu.kotlin.self

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.LogUtils
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.self.R
import com.andryu.kotlin.self.databinding.FragmentSelfContainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 系统自带 目录fragment
 */
@AndroidEntryPoint
class SelfContainFragment : BaseFragment() {

    private var _binding: FragmentSelfContainBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var mAdapter: SelfContainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelfContainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSelfContain.layoutManager = LinearLayoutManager(context)
        binding.rvSelfContain.adapter = mAdapter
        mAdapter.setOnItemClick {
            onFragmentClick(it.fragment)
        }
        binding.tvSelfBack.setOnClickListener {
            val backCount = childFragmentManager.backStackEntryCount
            LogUtils.d(LogUtils.SELF_TAG, "backStackEntryCount: $backCount")
            if (backCount > 1) {
                childFragmentManager.popBackStack()
            } else {
                binding.tvSelfBack.visibility = View.GONE
                binding.rvSelfContain.visibility = View.VISIBLE
                binding.rvSelfContain.visibility = View.GONE
            }
        }
    }


    @SuppressLint("CommitTransaction")
    private fun onFragmentClick(fragment: BaseFragment) {
        if (!binding.tvSelfBack.isVisible) {
            binding.tvSelfBack.visibility = View.VISIBLE
        }
        if (binding.rvSelfContain.isVisible) {
            binding.rvSelfContain.visibility = View.GONE
        }
        if (binding.flSelfContent.isVisible) {
            binding.flSelfContent.visibility = View.VISIBLE
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_self_content, fragment)
            .addToBackStack(fragment::class.java.name)
            .commitAllowingStateLoss()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}