package com.hk.iwanttobesupermvp.di.networkmodule

import com.hk.iwanttobesupermvp.util.SampleKeyStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(SampleKeyStore.provideMockBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named(RXJAVA_RETROFIT)
    fun provideRxRetrofit() : Retrofit =
        Retrofit.Builder().baseUrl(SampleKeyStore.provideMockBaseUrl())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    companion object {
        const val RXJAVA_RETROFIT = "RxRetrofit"
    }
}