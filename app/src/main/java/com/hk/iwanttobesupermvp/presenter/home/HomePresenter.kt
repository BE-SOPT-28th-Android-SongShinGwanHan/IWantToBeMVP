package com.hk.iwanttobesupermvp.presenter.home

import com.hk.iwanttobesupermvp.contract.home.HomeContract
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private var homeView: HomeContract.HomeView,
    private val homeModel: HomeContract.HomeModel
) : HomeContract.HomePresenter {
    override fun onHomeMoreButtonClick() {
        homeView.showGithubRepositoryFragment()
    }

}