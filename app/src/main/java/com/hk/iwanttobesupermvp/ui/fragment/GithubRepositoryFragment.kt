package com.hk.iwanttobesupermvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hk.iwanttobesupermvp.R
import com.hk.iwanttobesupermvp.contract.fragment.githubrepository.GithubRepositoryFragmentContract
import com.hk.iwanttobesupermvp.databinding.FragmentGithubRepositoryBinding
import com.hk.iwanttobesupermvp.presenter.githubrepository.GithubRepositoryPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GithubRepositoryFragment @Inject constructor() : Fragment(),
    GithubRepositoryFragmentContract.GithubRepositoryView {

    private lateinit var binding: FragmentGithubRepositoryBinding

    @Inject
    lateinit var githubRepositoryPresenter : GithubRepositoryPresenter

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

        return binding.root
    }
}