package com.hk.iwanttobesupermvp.di.presentermodule

import com.hk.iwanttobesupermvp.contract.activity.home.HomeContract
import com.hk.iwanttobesupermvp.model.HomeModel
import com.hk.iwanttobesupermvp.presenter.home.HomePresenter
import com.hk.iwanttobesupermvp.ui.activity.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class HomeModule {
    @Binds
    abstract fun bindActivity(activity: HomeActivity): HomeContract.HomeView

    @Binds
    abstract fun bindModel(model: HomeModel): HomeContract.HomeModel

    @Binds
    abstract fun bindPresenter(presenter: HomePresenter): HomeContract.HomePresenter
}