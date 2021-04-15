package com.hk.iwanttobesupermvp.domain.repository

import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity

interface MockRepository {
    suspend fun emitMockData(): List<MockDataEntity>
}