package com.hk.iwanttobesupermvp.presenter.githubrepository

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.api.data.mock.asMockEntityData
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.domain.repository.MockRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoryPresenter @Inject constructor(
    private val githubRepositoryView: GithubRepositoryFragmentContract.GithubRepositoryView,
    private val githubRepositoryModel: GithubRepositoryFragmentContract.GithubRepositoryModel,
    private val mockRepository: MockRepository
) : GithubRepositoryFragmentContract.GithubRepositoryPresenter {
    override fun fetchMockData() {
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

    override fun setRecyclerView() {
        githubRepositoryView.initializeRecyclerView()
    }

    override fun getNetworkedData() =
        githubRepositoryModel.getMockData()

}