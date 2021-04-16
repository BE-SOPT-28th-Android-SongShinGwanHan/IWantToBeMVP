package com.hk.iwanttobesupermvp.domain.repository

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import retrofit2.Call

interface MockRepository {
    suspend fun fetchMockDataWithCoroutine(): List<MockDataEntity>

    fun fetchMockDataWithCall(): Call<List<MockDataDTO>>
}