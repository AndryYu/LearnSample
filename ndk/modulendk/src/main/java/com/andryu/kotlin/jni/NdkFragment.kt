package com.andryu.kotlin.jni

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.LogUtils
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.jni.databinding.FragmentNdkBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NdkFragment : BaseFragment() {

    private var _binding: FragmentNdkBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var mAdapter: NdkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNdkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNdkCategory.layoutManager = LinearLayoutManager(context)
        binding.rvNdkCategory.adapter = mAdapter
        mAdapter.setOnItemClick {
            onFragmentClick(it.fragment)
        }
        binding.tvNdkBack.setOnClickListener {
            val backCount = childFragmentManager.backStackEntryCount
            LogUtils.d(LogUtils.NDK_TAG, "backStackEntryCount: $backCount")
            if (backCount > 1) {
                childFragmentManager.popBackStack()
            } else {
                binding.tvNdkBack.visibility = View.GONE
                binding.rvNdkCategory.visibility = View.VISIBLE
                binding.flNdkContent.visibility = View.GONE
            }
        }
    }

    @SuppressLint("CommitTransaction")
    private fun onFragmentClick(fragment: BaseFragment) {
        if (!binding.tvNdkBack.isVisible) {
            binding.tvNdkBack.visibility = View.VISIBLE
        }
        if (binding.rvNdkCategory.isVisible) {
            binding.rvNdkCategory.visibility = View.GONE
        }
        if (binding.flNdkContent.isVisible) {
            binding.flNdkContent.visibility = View.VISIBLE
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_ndk_content, fragment)
            .addToBackStack(fragment::class.java.name)
            .commitAllowingStateLoss()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}