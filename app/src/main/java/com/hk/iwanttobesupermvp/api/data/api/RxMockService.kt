package com.hk.iwanttobesupermvp.api.data.api

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import io.reactivex.Observable
import retrofit2.http.GET

interface RxMockService {
    @GET("users")
    fun getUsersWithFromFuture(): Observable<List<MockDataDTO>>
}