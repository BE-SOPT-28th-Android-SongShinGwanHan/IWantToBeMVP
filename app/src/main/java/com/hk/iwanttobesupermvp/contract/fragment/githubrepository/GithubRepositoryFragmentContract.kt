package com.hk.iwanttobesupermvp.contract.fragment.githubrepository

import androidx.lifecycle.MutableLiveData
import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity

interface GithubRepositoryFragmentContract {
    interface GithubRepositoryView {
        fun setMockAdapter(mockDataList: MutableList<MockDataEntity>)

        fun initializeRecyclerView()
    }

    interface GithubRepositoryModel {
        fun setMockData(_mockDataList: List<MockDataDTO>)

        fun getMockData(): List<MockDataDTO>
    }

    interface GithubRepositoryPresenter {
        fun fetchMockDataWithCall()

        fun fetchMockDataWithCoroutine()

        fun setRecyclerView()

        fun getNetworkedData(): List<MockDataDTO>
    }
}