package com.hk.iwanttobesupermvp.di.repositorymodule

import com.hk.iwanttobesupermvp.api.data.repository.AuthRepositoryImpl
import com.hk.iwanttobesupermvp.api.data.repository.MockRepositoryImpl
import com.hk.iwanttobesupermvp.domain.repository.AuthRepository
import com.hk.iwanttobesupermvp.domain.repository.MockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMockRepository(repository: MockRepositoryImpl): MockRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(repository : AuthRepositoryImpl) : AuthRepository
}