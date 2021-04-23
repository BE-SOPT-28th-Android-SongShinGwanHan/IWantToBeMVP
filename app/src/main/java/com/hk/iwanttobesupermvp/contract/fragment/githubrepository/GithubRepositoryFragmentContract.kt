package com.hk.iwanttobesupermvp.contract.fragment.githubrepository

import com.hk.iwanttobesupermvp.api.data.mock.MockDataDTO
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity

interface GithubRepositoryFragmentContract {
    // TODO : 이거 구조랑 함수이름은 다시 한번 생각을 해보셔야할거 같습니다 송훈기씨
    interface GithubRepositoryView {
        fun setGithubRepositoryAdapter(mockDataList: MutableList<MockDataEntity>)

        fun initializeRecyclerView()

        fun changeLayoutManager()
    }

    interface GithubRepositoryModel {
        fun setMockData(_mockDataList: List<MockDataDTO>)

        fun getMockData(): List<MockDataDTO>
    }

    interface GithubRepositoryPresenter {
        fun fetchMockDataWithCall()

        fun fetchMockDataWithCoroutine()

        fun fetchMockDataWithRxJava()

        fun setRecyclerView() //todo : 이건 행동의 이름으로 바꿔줘야지 니 맘대로 이케 적으면 되겠니? 훈기야

        fun getNetworkedData(): List<MockDataDTO>

        fun onChangeLayoutManagerButtonClick()
    }
}