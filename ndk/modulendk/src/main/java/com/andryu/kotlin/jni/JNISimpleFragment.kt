package com.andryu.kotlin.jni

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.jni.databinding.FragmentJniSimpleBinding

class JNISimpleFragment:BaseFragment() {

    private var _binding: FragmentJniSimpleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJniSimpleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}