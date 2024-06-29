package com.andryyu.kotlin.self

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.base.listener.IFragmentListener
import com.andryu.kotlin.self.databinding.FragmentSelfContainBinding

/**
 * 第三方库 目录fragment
 */
class SelfContainFragment : BaseFragment() {

    private var _binding: FragmentSelfContainBinding? = null
    private val binding get() = _binding!!
    private var mDataList = mutableListOf<LearnEntity>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelfContainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}