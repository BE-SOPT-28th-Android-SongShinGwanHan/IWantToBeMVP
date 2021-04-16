package com.hk.iwanttobesupermvp.di.presentermodule

import com.hk.iwanttobesupermvp.contract.activity.signup.SignUpContract
import com.hk.iwanttobesupermvp.model.SignUpModel
import com.hk.iwanttobesupermvp.presenter.signup.SignUpPresenter
import com.hk.iwanttobesupermvp.ui.activity.SignUpActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class SignUpModule {
    @Binds
    abstract fun bindActivity(activity: SignUpActivity): SignUpContract.SignUpView

    @Binds
    abstract fun bindModel(model: SignUpModel): SignUpContract.SignUpModel

    @Binds
    abstract fun bindPresenter(presenter: SignUpPresenter): SignUpContract.SignUpPresenter
}