package com.hk.iwanttobesupermvp.di.presentermodule

import android.app.Activity
import androidx.fragment.app.Fragment
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.model.GithubRepositoryModel
import com.hk.iwanttobesupermvp.presenter.githubrepository.GithubRepositoryPresenter
import com.hk.iwanttobesupermvp.ui.fragment.GithubRepositoryFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class GithubRepositoryModule {
    @Binds
    abstract fun bindPresenter(presenter : GithubRepositoryPresenter) : GithubRepositoryFragmentContract.GithubRepositoryPresenter

    @Binds
    abstract fun bindModel(model : GithubRepositoryModel) : GithubRepositoryFragmentContract.GithubRepositoryModel
}

@InstallIn(FragmentComponent::class)
@Module
abstract class GithubRepositoryFragmentModule {
    @Binds
    abstract fun bindFragment(fragment : GithubRepositoryFragment) : GithubRepositoryFragmentContract.GithubRepositoryView
}