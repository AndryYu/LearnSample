package com.andryu.kotlin.third

import com.andryu.kotlin.base.entity.LearnEntity
import com.andryu.kotlin.rxjava.RxjavaCategoryFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@InstallIn(FragmentComponent::class)
@Module
class ThirdPartyModule {

    @Provides
    @Named("thirdList")
    fun provideThirdList():MutableList<LearnEntity>{
        val dataList = mutableListOf<LearnEntity>()
        dataList.add(LearnEntity("Rxjava", RxjavaCategoryFragment()))
        return dataList
    }

}