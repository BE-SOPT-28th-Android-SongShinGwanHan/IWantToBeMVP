package com.hk.iwanttobesupermvp.api.data.entity.signup

data class ResponseSignUp(
    val success : Boolean,
    val message : String,
    val data : SignUpData
) {
    data class SignUpData(
        val user_nickname : String
    )
}
