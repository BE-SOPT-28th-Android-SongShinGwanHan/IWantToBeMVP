package com.hk.iwanttobesupermvp.presenter.githubrepository

import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.domain.repository.MockRepository
import javax.inject.Inject

class GithubRepositoryPresenter @Inject constructor(
    private val githubRepositoryView: GithubRepositoryFragmentContract.GithubRepositoryView,
    private val githubRepositoryModel: GithubRepositoryFragmentContract.GithubRepositoryModel,
    private val mockRepository : MockRepository
) : GithubRepositoryFragmentContract.GithubRepositoryPresenter{

}