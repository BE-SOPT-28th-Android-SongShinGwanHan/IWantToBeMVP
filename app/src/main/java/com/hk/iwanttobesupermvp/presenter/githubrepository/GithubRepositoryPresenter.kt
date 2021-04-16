package com.hk.iwanttobesupermvp.presenter.githubrepository

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.api.data.mock.asMockEntityData
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import com.hk.iwanttobesupermvp.domain.repository.MockRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoryPresenter @Inject constructor(
    private val githubRepositoryView: GithubRepositoryFragmentContract.GithubRepositoryView,
    private val githubRepositoryModel: GithubRepositoryFragmentContract.GithubRepositoryModel,
    private val mockRepository: MockRepository
) : GithubRepositoryFragmentContract.GithubRepositoryPresenter {
    override fun fetchMockDataWithCall() {
        mockRepository.fetchMockDataWithCall().enqueue(object : Callback<List<MockDataDTO>> {
            override fun onResponse(
                call: Call<List<MockDataDTO>>,
                response: Response<List<MockDataDTO>>
            ) {
                response.body()?.asMockEntityData()?.let { githubRepositoryView.setMockAdapter(it) }
            }

            override fun onFailure(call: Call<List<MockDataDTO>>, t: Throwable) {
                throw t
            }
        })
    }

    override fun fetchMockDataWithCoroutine() {
        // 왜 코루틴은 uiScope에서 작업을 해도 되는지에 대한 것이 중요한 점입니다.
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)
        uiScope.launch {
            githubRepositoryView.setMockAdapter(mockRepository.fetchMockDataWithCoroutine() as MutableList<MockDataEntity>)
        }
    }

    override fun fetchMockDataWithRxJava() {
        mockRepository.fetchMockDataWithRxJava().get()
            .subscribeOn(Schedulers.io()) // 어디서 구독해서 작업하고
            .observeOn(AndroidSchedulers.mainThread()) // 어디서 값을 가져와 관찰할것인지
            .subscribe(object : Observer<List<MockDataDTO>> { // 구독하여 작업할 떄 어떤 작업을 할 것인지에 대한 작업 명세
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: List<MockDataDTO>) {
                    githubRepositoryView.setMockAdapter(t.asMockEntityData())
                }

                override fun onError(e: Throwable) {}

                override fun onComplete() {}
            })
    }

    override fun setRecyclerView() {
        githubRepositoryView.initializeRecyclerView()
    }

    override fun getNetworkedData() =
        githubRepositoryModel.getMockData()

}