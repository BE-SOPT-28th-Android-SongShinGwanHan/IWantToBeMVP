package com.hk.iwanttobesupermvp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.api.data.mock.asMockEntityData
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import javax.inject.Inject

class GithubRepositoryModel @Inject constructor() :
    GithubRepositoryFragmentContract.GithubRepositoryModel {

    var mockDataList = mutableListOf<MockDataDTO>()

    override fun setMockData(_mockDataList: List<MockDataDTO>) {
        mockDataList = _mockDataList as MutableList<MockDataDTO>
    }

    override fun getMockData(): List<MockDataDTO> = mockDataList

}