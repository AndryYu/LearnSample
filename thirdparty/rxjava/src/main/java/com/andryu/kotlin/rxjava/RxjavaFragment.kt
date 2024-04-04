package com.andryu.kotlin.rxjava

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.base.listener.IFragmentListener
import com.andryu.kotlin.rxjava.databinding.FragmentThirdRxjavaBinding

class RxjavaFragment : BaseFragment() {

    private lateinit var binding: FragmentThirdRxjavaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdRxjavaBinding.inflate(inflater, container, false)
        return binding.root
    }

}