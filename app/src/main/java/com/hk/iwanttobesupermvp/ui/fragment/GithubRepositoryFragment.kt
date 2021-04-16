package com.hk.iwanttobesupermvp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hk.iwanttobesupermvp.R
import com.hk.iwanttobesupermvp.databinding.FragmentGithubRepositoryBinding

class GithubRepositoryFragment : Fragment() {

    private lateinit var binding : FragmentGithubRepositoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_github_repository,container,false)

        return binding.root
    }
}