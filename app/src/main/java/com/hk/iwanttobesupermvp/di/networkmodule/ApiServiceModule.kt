package com.hk.iwanttobesupermvp.di.networkmodule

import com.hk.iwanttobesupermvp.api.data.api.MockService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideMockService(retrofit : Retrofit) : MockService = retrofit.create(MockService::class.java)
}