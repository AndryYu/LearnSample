package com.andryu.kotlin.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.jetpack.databinding.FragmentJetpackHiltBinding

class JetpackHiltFragment : BaseFragment() {
    private var _binding: FragmentJetpackHiltBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJetpackHiltBinding.inflate(inflater, container, false)
        return binding.root
    }
}