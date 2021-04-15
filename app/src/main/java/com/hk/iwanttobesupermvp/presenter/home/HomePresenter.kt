package com.hk.iwanttobesupermvp.presenter.home

import com.hk.iwanttobesupermvp.contract.home.HomeContract
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private var HomeView: HomeContract.HomeView,
    private val HomeModel: HomeContract.HomeModel
) : HomeContract.HomePresenter {
    override fun testPresenter() {

    }
}