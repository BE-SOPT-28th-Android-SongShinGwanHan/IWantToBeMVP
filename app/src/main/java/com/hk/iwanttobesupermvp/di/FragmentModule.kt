package com.hk.iwanttobesupermvp.di

import androidx.fragment.app.Fragment
import com.hk.iwanttobesupermvp.ui.fragment.GithubRepositoryFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
object FragmentModule {
    @Provides
    fun provideGithubRepositoryFragment(fragment: Fragment): GithubRepositoryFragment =
        fragment as GithubRepositoryFragment
}