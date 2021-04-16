package com.hk.iwanttobesupermvp.presenter.githubrepository

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.api.data.mock.asMockEntityData
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import com.hk.iwanttobesupermvp.domain.repository.MockRepository
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
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)
        uiScope.launch {
            githubRepositoryView.setMockAdapter(mockRepository.fetchMockDataWithCoroutine() as MutableList<MockDataEntity>)
        }
    }

    override fun setRecyclerView() {
        githubRepositoryView.initializeRecyclerView()
    }

    override fun getNetworkedData() =
        githubRepositoryModel.getMockData()

}