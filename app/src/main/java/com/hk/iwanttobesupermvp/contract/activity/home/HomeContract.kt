package com.hk.iwanttobesupermvp.contract.activity.home

interface HomeContract {
    interface HomeView {
        fun initializeView()

        fun showGithubRepositoryFragment()
    }

    interface HomeModel {
        // 얘가 뭘 해야할지에 대해서는 좀 더 생각을 해보자구용~
        fun testModel()
    }

    interface HomePresenter {
        fun onHomeMoreButtonClick()
    }
}