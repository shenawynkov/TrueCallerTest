package com.shenawynkov.truecallertest.di

import com.shenawynkov.truecallertest.data.HomeRepoImpl
import com.shenawynkov.truecallertest.domain.ApiService
import com.shenawynkov.truecallertest.domain.repository.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideHomeRepository(api: ApiService): HomeRepo {
        return HomeRepoImpl(api)
    }

}