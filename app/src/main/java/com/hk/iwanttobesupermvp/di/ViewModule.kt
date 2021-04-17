package com.hk.iwanttobesupermvp.di

import android.app.Activity
import com.hk.iwanttobesupermvp.ui.activity.HomeActivity
import com.hk.iwanttobesupermvp.ui.activity.SignInActivity
import com.hk.iwanttobesupermvp.ui.activity.SignUpActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ViewModule {
    @Provides
    fun provideSignUpActivity(activity: Activity): SignUpActivity = activity as SignUpActivity

    @Provides
    fun provideSignInActivity(activity: Activity): SignInActivity = activity as SignInActivity

    @Provides
    fun provideHomeActivity(activity: Activity) : HomeActivity = activity as HomeActivity
}