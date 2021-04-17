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
                response.body()?.asMockEntityData()
                    ?.let { githubRepositoryView.setGithubRepositoryAdapter(it) }
            }

            override fun onFailure(call: Call<List<MockDataDTO>>, t: Throwable) {
                throw t
            }
        })
    }

    override fun fetchMockDataWithCoroutine() {
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)
        uiScope.launch {
            githubRepositoryView.setGithubRepositoryAdapter(mockRepository.fetchMockDataWithCoroutine() as MutableList<MockDataEntity>)
        }
    }

    override fun fetchMockDataWithRxJava() {
        mockRepository.fetchMockDataWithRxJava().get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<MockDataDTO>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: List<MockDataDTO>) {
                    githubRepositoryView.setGithubRepositoryAdapter(t.asMockEntityData())
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