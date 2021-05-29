package com.hk.iwanttobesupermvp.api.data.entity.signup

import com.google.gson.annotations.SerializedName

data class RequestSignUp(
    @SerializedName("email")
    val id : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("sex")
    val sex : String,
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("phone")
    val phone : String,
    @SerializedName("birth")
    val birth : String
)
