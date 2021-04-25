package com.hk.iwanttobesupermvp.api.data.repository

import com.hk.iwanttobesupermvp.api.data.api.MockService
import com.hk.iwanttobesupermvp.api.data.api.RxMockService
import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.api.data.mock.asMockEntityData
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import com.hk.iwanttobesupermvp.domain.repository.MockRepository
import io.reactivex.Observable
import retrofit2.Call
import java.util.concurrent.*
import javax.inject.Inject

class MockRepositoryImpl @Inject constructor(
    private val service: MockService,
    private val rxService: RxMockService
) : MockRepository {
    override suspend fun fetchMockDataWithCoroutine(): List<MockDataEntity> {
        return service.getUsersWithCoroutine().asMockEntityData()
    }

    override fun fetchMockDataWithCall(): Call<List<MockDataDTO>> = service.getUsersWithCall()

    override fun fetchMockDataWithRxJava(): Future<Observable<List<MockDataDTO>>> {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val mockDataCallable = Callable {
            rxService.getUsersWithFromFuture()
        }

        return object : Future<Observable<List<MockDataDTO>>> {
            override fun cancel(mayInterruptIfRunning: Boolean): Boolean {
                if (mayInterruptIfRunning) {
                    executor.shutdown()
                }
                return false
            }

            override fun isCancelled(): Boolean = executor.isShutdown

            override fun isDone(): Boolean = executor.isTerminated

            override fun get(): Observable<List<MockDataDTO>> =
                executor.submit(mockDataCallable).get()

            override fun get(timeout: Long, unit: TimeUnit?): Observable<List<MockDataDTO>>? =
                executor.submit(mockDataCallable).get(timeout, unit)
        }
    }
}