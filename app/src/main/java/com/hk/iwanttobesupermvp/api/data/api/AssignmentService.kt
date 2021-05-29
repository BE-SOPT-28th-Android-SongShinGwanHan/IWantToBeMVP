package com.hk.iwanttobesupermvp.api.data.api

import com.hk.iwanttobesupermvp.api.data.entity.signin.RequestSignIn
import com.hk.iwanttobesupermvp.api.data.entity.signin.ResponseSignIn
import com.hk.iwanttobesupermvp.api.data.entity.signup.RequestSignUp
import com.hk.iwanttobesupermvp.api.data.entity.signup.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AssignmentService {

    @POST("login/signin")
    fun signIn(
        @Body requestSignIn: RequestSignIn
    ) : Call<ResponseSignIn>

    @POST("login/signup")
    fun signUp(
        @Body requestSignUp : RequestSignUp
    ) : Call<ResponseSignUp>
}