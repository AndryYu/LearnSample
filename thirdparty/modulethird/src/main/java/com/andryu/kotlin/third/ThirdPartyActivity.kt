package com.andryu.kotlin.third

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.base.listener.IFragmentListener
import com.andryu.kotlin.third.databinding.ActivityThirdPartyBinding

/**
 * 第三方开源
 */
class ThirdPartyActivity : AppCompatActivity(),IFragmentListener {

    private lateinit var binding: ActivityThirdPartyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdPartyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        setUpContent()
    }

    /**
     * 初始化toolbar
     */
    private fun initToolbar() {
        binding.toolbar.run {
            title = resources.getString(com.andryu.kotlin.ui.R.string.txt_third_party)
            setTitleTextColor(resources.getColor(com.andryu.kotlin.ui.R.color.white, null))
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * 设置显示fragment
     */
    private fun setUpContent() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_third_party, ThirdPartyFragment())
            .addToBackStack("thirdParty")
            .commitAllowingStateLoss()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
        return super.onSupportNavigateUp()
    }

    override fun onFragmentClick(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_third_party, fragment)
            .addToBackStack(fragment::class.java.name)
            .commitAllowingStateLoss()
    }

}