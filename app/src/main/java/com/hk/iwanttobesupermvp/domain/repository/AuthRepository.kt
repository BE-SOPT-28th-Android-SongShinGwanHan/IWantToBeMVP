package com.hk.iwanttobesupermvp.domain.repository

import com.hk.iwanttobesupermvp.api.data.entity.signin.RequestSignIn
import com.hk.iwanttobesupermvp.api.data.entity.signin.ResponseSignIn
import com.hk.iwanttobesupermvp.api.data.entity.signup.RequestSignUp
import com.hk.iwanttobesupermvp.api.data.entity.signup.ResponseSignUp
import retrofit2.Call

interface AuthRepository {
    fun signIn(requestSignIn: RequestSignIn) : Call<ResponseSignIn>

    fun signUp(requestSignUp: RequestSignUp) : Call<ResponseSignUp>
}