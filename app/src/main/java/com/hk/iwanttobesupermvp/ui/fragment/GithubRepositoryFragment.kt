package com.hk.iwanttobesupermvp.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hk.iwanttobesupermvp.R
import com.hk.iwanttobesupermvp.api.data.mock.asMockEntityData
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.databinding.FragmentGithubRepositoryBinding
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity
import com.hk.iwanttobesupermvp.presenter.githubrepository.GithubRepositoryPresenter
import com.hk.iwanttobesupermvp.ui.adapter.GithubRepositoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GithubRepositoryFragment @Inject constructor() : Fragment(),
    GithubRepositoryFragmentContract.GithubRepositoryView {

    private lateinit var binding: FragmentGithubRepositoryBinding

    private lateinit var githubRepositoryAdapter: GithubRepositoryAdapter

    @Inject
    lateinit var githubRepositoryPresenter: GithubRepositoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_github_repository,
            container,
            false
        )
        githubRepositoryAdapter = GithubRepositoryAdapter()
        githubRepositoryPresenter.setRecyclerView()
        githubRepositoryPresenter.fetchMockData()
        githubRepositoryAdapter.notifyDataSetChanged()
        return binding.root
    }

    override fun setMockAdapter(mockDataList: MutableList<MockDataEntity>) {
        githubRepositoryAdapter.apply {
            mockRepositoryList = mockDataList
            notifyDataSetChanged()
        }
    }

    override fun initializeRecyclerView() {
        binding.fragmentGithubRepositoryRecyclerView.apply {
            adapter = githubRepositoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}