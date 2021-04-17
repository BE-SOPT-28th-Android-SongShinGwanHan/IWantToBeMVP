package com.hk.iwanttobesupermvp.contract.activity.home

interface HomeContract {
    interface HomeView {
        fun initializeView()

        fun showGithubRepositoryFragment()
    }

    interface HomeModel {
        fun testModel()
    }

    interface HomePresenter {
        fun onHomeMoreButtonClick()
    }
}