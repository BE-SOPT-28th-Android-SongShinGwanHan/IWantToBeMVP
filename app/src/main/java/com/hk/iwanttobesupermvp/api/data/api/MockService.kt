package com.hk.iwanttobesupermvp.api.data.api

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import retrofit2.http.GET

interface MockService {
    @GET("users")
    suspend fun getUsers(): List<MockDataDTO>
}