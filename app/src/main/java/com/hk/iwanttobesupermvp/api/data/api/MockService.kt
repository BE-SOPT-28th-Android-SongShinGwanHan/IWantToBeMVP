package com.hk.iwanttobesupermvp.api.data.api

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import retrofit2.Call
import retrofit2.http.GET

interface MockService {
    // Use Coroutine
    @GET("users")
    suspend fun getUsersWithCoroutine(): List<MockDataDTO>

    @GET("users")
    fun getUsersWithCall(): Call<List<MockDataDTO>>

}