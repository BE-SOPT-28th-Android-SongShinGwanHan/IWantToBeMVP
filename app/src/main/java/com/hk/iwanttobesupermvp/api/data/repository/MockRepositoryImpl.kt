package com.hk.iwanttobesupermvp.api.data.repository

import com.hk.iwanttobesupermvp.api.data.api.MockService
import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.api.data.mock.asMockEntityData
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import com.hk.iwanttobesupermvp.domain.repository.MockRepository
import retrofit2.Call
import javax.inject.Inject

class MockRepositoryImpl @Inject constructor(private val service: MockService) : MockRepository {
    override suspend fun emitMockDataWithCoroutine(): List<MockDataEntity> {
        return service.getUsersWithCoroutine().asMockEntityData()
    }

    override fun emitMockDataWithCall(): Call<List<MockDataDTO>> {
        return service.getUsersWithCall()
    }
}