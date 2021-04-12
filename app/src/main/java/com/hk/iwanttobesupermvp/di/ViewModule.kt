package com.hk.iwanttobesupermvp.di

import android.app.Activity
import com.hk.iwanttobesupermvp.view.activity.SignUpActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ViewModule {
    @Provides
    fun provideSignUpActivity(activity: Activity): SignUpActivity = activity as SignUpActivity
}