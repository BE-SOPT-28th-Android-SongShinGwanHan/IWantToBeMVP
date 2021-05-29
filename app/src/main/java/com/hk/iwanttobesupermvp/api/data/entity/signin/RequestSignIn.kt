package com.hk.iwanttobesupermvp.api.data.entity.signin

import com.google.gson.annotations.SerializedName

data class RequestSignIn(
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String
)
