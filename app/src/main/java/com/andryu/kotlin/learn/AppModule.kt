package com.andryu.kotlin.learn

import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.jni.NdkFragment
import com.andryu.kotlin.third.ThirdPartyFragment
import com.andryyu.kotlin.self.SelfContainFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@InstallIn(ActivityComponent::class)
@Module
class AppModule {
    @Provides
    @Named("categoryList")
    fun provideCategoryList(): MutableList<LearnEntity> {
        val mDataList = mutableListOf<LearnEntity>()
        mDataList.add(LearnEntity("Third 开源库", ThirdPartyFragment()))
        mDataList.add(LearnEntity("Android 系统自带", SelfContainFragment()))
        mDataList.add(LearnEntity("Ndk 开发", NdkFragment()))
        mDataList.add(LearnEntity("OpenGL和Vulkan使用", NdkFragment()))
        return mDataList
    }
}