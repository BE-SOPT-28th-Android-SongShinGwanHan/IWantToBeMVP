package com.hk.iwanttobesupermvp.api.data.repository

import com.hk.iwanttobesupermvp.api.data.api.MockService
import com.hk.iwanttobesupermvp.api.data.mock.asMockEntityData
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import com.hk.iwanttobesupermvp.domain.repository.MockRepository
import javax.inject.Inject

class MockRepositoryImpl @Inject constructor(private val service: MockService) : MockRepository {
    override suspend fun emitMockData(): List<MockDataEntity> {
        return service.getUsers().asMockEntityData()
    }
}