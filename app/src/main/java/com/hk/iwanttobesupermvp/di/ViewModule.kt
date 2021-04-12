package com.hk.iwanttobesupermvp.di

import android.app.Activity
import com.hk.iwanttobesupermvp.view.activity.SignInActivity
import com.hk.iwanttobesupermvp.view.activity.SignUpActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

// activity를 결국에는 주입을 해줘야 함
@Module
@InstallIn(ActivityComponent::class)
object ViewModule {
    @Provides
    fun provideSignUpActivity(activity: Activity): SignUpActivity = activity as SignUpActivity

    @Provides
    fun provideSignInActivity(activity : Activity) : SignInActivity = activity as SignInActivity
}
