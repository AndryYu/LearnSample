package com.andryu.kotlin.jni

import com.andryu.kotlin.base.entity.LearnEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@InstallIn(FragmentComponent::class)
@Module
class NDKModule {
    @Provides
    @Named("ndkList")
    fun provideNdkList(): MutableList<LearnEntity> {
        val mDataList = mutableListOf<LearnEntity>()
        mDataList.add(LearnEntity("JNI-Simple 使用", JNISimpleFragment()))
        return mDataList
    }
}