package com.hk.iwanttobesupermvp.domain.repository

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import io.reactivex.Observable
import retrofit2.Call
import java.util.concurrent.Future

interface MockRepository {
    suspend fun fetchMockDataWithCoroutine(): List<MockDataEntity>

    fun fetchMockDataWithCall(): Call<List<MockDataDTO>>

    fun fetchMockDataWithRxJava(): Future<Observable<List<MockDataDTO>>>
}