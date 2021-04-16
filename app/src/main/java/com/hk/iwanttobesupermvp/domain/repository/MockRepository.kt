package com.hk.iwanttobesupermvp.domain.repository

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import retrofit2.Call
import retrofit2.Response

interface MockRepository {
    suspend fun fetchMockDataWithCoroutine(): List<MockDataEntity>

    // Call 객체로 감싸져서 나오기 떄문에 형변환을 해주기가 좀 어렵다
    // presenter layer에서 형변환하여 데이터를 넣어주도록 하면 좋을거 같다
    fun fetchMockDataWithCall() : Call<List<MockDataDTO>>
}