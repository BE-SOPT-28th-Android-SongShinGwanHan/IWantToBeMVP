package com.hk.iwanttobesupermvp.api.data.entity.signin

import com.google.gson.annotations.SerializedName

data class ResponseSignIn(
    val success : Boolean,
    val message : String,

){
    data class userData(
        @SerializedName("UserId")
        val userId : Int,
        @SerializedName("user_nickname")
        val userNickname : String,
        @SerializedName("token")
        val token : String
    )
}
