package com.andryyu.kotlin.self

import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.jetpack.JetpackHiltFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@InstallIn(FragmentComponent::class)
@Module
class SelfContainModule {
    @Provides
    @Named("selfList")
    fun provideSelfContainList(): MutableList<LearnEntity> {
        val mDataList = mutableListOf<LearnEntity>()
        mDataList.add(LearnEntity("Jetpack-Hilt使用", JetpackHiltFragment()))
        return mDataList
    }
}