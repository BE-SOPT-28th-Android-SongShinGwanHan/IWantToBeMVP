package com.hk.iwanttobesupermvp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hk.iwanttobesupermvp.R
import com.hk.iwanttobesupermvp.contract.home.HomeContract
import com.hk.iwanttobesupermvp.databinding.ActivityHomeBinding
import com.hk.iwanttobesupermvp.presenter.home.HomePresenter
import com.hk.iwanttobesupermvp.ui.fragment.GithubRepositoryFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity @Inject constructor() : AppCompatActivity(), HomeContract.HomeView {
    private lateinit var binding : ActivityHomeBinding

    @Inject
    lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()

        binding.homeMoreButton.setOnClickListener {
            homePresenter.onHomeMoreButtonClick()
        }
    }

    override fun initializeView() {
        intent.getStringExtra("id")?.let{
            binding.homeIdSmallText.text = it
            binding.homeIdMediumText.text = it
        }
    }

    override fun showGithubRepositoryFragment() {
        val githubRepositoryFragment = GithubRepositoryFragment()
        val transAction = supportFragmentManager.beginTransaction()
        transAction.replace(R.id.home_fragment_container_view,githubRepositoryFragment).commit()
    }
}