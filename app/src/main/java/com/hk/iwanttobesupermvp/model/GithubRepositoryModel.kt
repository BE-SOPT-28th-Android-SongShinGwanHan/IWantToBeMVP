package com.hk.iwanttobesupermvp.model

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import javax.inject.Inject

class GithubRepositoryModel @Inject constructor() :
    GithubRepositoryFragmentContract.GithubRepositoryModel {

    var mockDataList = mutableListOf<MockDataDTO>()

    override fun setMockData(_mockDataList: List<MockDataDTO>) {
        mockDataList = _mockDataList as MutableList<MockDataDTO>
    }

    override fun getMockData(): List<MockDataDTO> = mockDataList

}