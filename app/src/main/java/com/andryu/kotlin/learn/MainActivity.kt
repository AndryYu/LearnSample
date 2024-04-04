package com.andryu.kotlin.learn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andryu.kotlin.learn.databinding.ActivityMainBinding
import com.andryu.kotlin.third.ThirdPartyActivity
import com.andryyu.kotlin.self.SelfContainActivity

/**
 * 首页
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.layoutContent.tvSelfContain.setOnClickListener {
            startActivity(Intent(this, SelfContainActivity::class.java))
        }
        binding.layoutContent.tvThirdParty.setOnClickListener {
            startActivity(Intent(this, ThirdPartyActivity::class.java))
        }
    }
}