package com.hk.iwanttobesupermvp.contract.fragment.githubrepository

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity

interface GithubRepositoryFragmentContract {
    interface GithubRepositoryView {
        // View에선 뭘해야할까?
        fun setMockAdapter(mockDataList : MutableList<MockDataEntity>)

        fun initializeRecyclerView()
    }

    interface GithubRepositoryModel {
        // Model에선 뭘 해야할까?
        fun setMockData(_mockDataList : List<MockDataDTO>)

        fun getMockData() : List<MockDataDTO>
    }

    interface GithubRepositoryPresenter {
        // Presenter에서는 뭘 해야할까?
        fun fetchMockData()

        fun setRecyclerView()

        fun getNetworkedData(): List<MockDataDTO>
    }
}