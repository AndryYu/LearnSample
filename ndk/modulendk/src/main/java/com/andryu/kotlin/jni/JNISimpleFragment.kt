package com.andryu.kotlin.jni

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import com.andryu.kotlin.base.fragment.BaseFragment
import com.andryu.kotlin.jni.databinding.FragmentJniSimpleBinding
import java.util.Locale

class JNISimpleFragment : BaseFragment() {

    private var _binding: FragmentJniSimpleBinding? = null
    private val binding get() = _binding!!
    private var mHour: Int = 0
    private var mMinute: Int = 0
    private var mSecond: Int = 0

    companion object {
        init {
            System.loadLibrary("simple")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJniSimpleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHelloJniResult.text = NativeLib.stringFromJNI()

    }

    override fun onResume() {
        super.onResume()
        NativeLib.startTicks(this)
    }

    @SuppressLint("SetTextI18n")
    @Keep
    fun updateTicks() {
        mSecond++
        if (mSecond > 60) {
            mMinute++
            mSecond -= 60
            if (mMinute > 60) {
                mHour++
                mMinute -= 60
            }
        }
        binding.tvJniCallbackResult.post {
            var strHour = "00"
            var strMinute = "00"
            var strSecond = "00"
            strHour = if (mHour < 10) {
                "0$mHour"
            } else {
                "$mHour"
            }
            strMinute = if (mMinute < 10) {
                "0$mMinute"
            } else {
                "$mMinute"
            }
            strSecond = if (mSecond < 10) {
                "0$mSecond"
            } else {
                "$mSecond"
            }
            binding.tvJniCallbackResult.text = "$strHour:$strMinute:$strSecond"
        }
    }

    @SuppressLint("SetTextI18n")
    @Keep
    private fun updateStatus(msg: String) {
        binding.tvJniCallbackStatus.post {
            if (msg.lowercase(Locale.getDefault()).contains("error")) {
                binding.tvJniCallbackStatus.text = "Native Err: $msg"
            } else {
                binding.tvJniCallbackStatus.text = "Native Msg: $msg"
            }
        }
    }

    override fun onPause() {
        super.onPause()
        NativeLib.stopTicks(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}