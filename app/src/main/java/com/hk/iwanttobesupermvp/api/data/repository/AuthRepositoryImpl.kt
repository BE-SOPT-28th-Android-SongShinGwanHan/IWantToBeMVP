package com.hk.iwanttobesupermvp.api.data.repository

import com.hk.iwanttobesupermvp.api.data.api.AssignmentService
import com.hk.iwanttobesupermvp.api.data.entity.signin.RequestSignIn
import com.hk.iwanttobesupermvp.api.data.entity.signin.ResponseSignIn
import com.hk.iwanttobesupermvp.api.data.entity.signup.RequestSignUp
import com.hk.iwanttobesupermvp.api.data.entity.signup.ResponseSignUp
import com.hk.iwanttobesupermvp.domain.repository.AuthRepository
import retrofit2.Call
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service : AssignmentService
) : AuthRepository {
    override fun signIn(requestSignIn: RequestSignIn): Call<ResponseSignIn> =
        service.signIn(requestSignIn)


    override fun signUp(requestSignUp: RequestSignUp): Call<ResponseSignUp> =
        service.signUp(requestSignUp)

}