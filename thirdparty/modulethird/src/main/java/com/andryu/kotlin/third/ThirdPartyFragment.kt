package com.andryu.kotlin.third

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.rxjava.RxjavaCategoryFragment
import com.andryu.kotlin.third.databinding.FragmentThirdLibraryBinding

/**
 * 第三方库 目录fragment
 */
class ThirdPartyFragment : BaseFragment() {

    private var _binding: FragmentThirdLibraryBinding? = null
    private val binding get() = _binding!!
    private var mDataList = mutableListOf<LearnEntity>()
    private var mAdapter: ThirdPartyAdapter? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

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
    }

    private fun onFragmentClick(fragment: BaseFragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl_third_content, fragment)
            .addToBackStack(fragment::class.java.name)
            .commitAllowingStateLoss()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mDataList.clear()
        mDataList.add(LearnEntity("Rxjava", RxjavaCategoryFragment()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}