package com.andryu.kotlin.learn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.jni.NdkFragment
import com.andryu.kotlin.learn.databinding.ActivityMainBinding
import com.andryu.kotlin.third.ThirdPartyFragment
import com.andryyu.kotlin.self.SelfContainFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 首页
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = mAdapter
        mAdapter.setOnItemClick {
            onFragmentClick(it.fragment)
        }
        //默认选中第一个
        onFragmentClick(ThirdPartyFragment())
    }


    private fun onFragmentClick(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_main, fragment)
            .addToBackStack(fragment::class.java.name)
            .commitAllowingStateLoss()
    }
}