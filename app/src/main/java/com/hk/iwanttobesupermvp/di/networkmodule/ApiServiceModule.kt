package com.hk.iwanttobesupermvp.di.networkmodule

import com.hk.iwanttobesupermvp.api.data.api.MockService
import com.hk.iwanttobesupermvp.api.data.api.RxMockService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideMockService(retrofit: Retrofit): MockService =
        retrofit.create(MockService::class.java)

    @Provides
    @Singleton
    fun provideRxMockService(@Named(RetrofitModule.RXJAVA_RETROFIT) retrofit: Retrofit): RxMockService =
        retrofit.create(RxMockService::class.java)
}