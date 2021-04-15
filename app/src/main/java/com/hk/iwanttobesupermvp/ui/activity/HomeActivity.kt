package com.hk.iwanttobesupermvp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hk.iwanttobesupermvp.contract.home.HomeContract
import com.hk.iwanttobesupermvp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity @Inject constructor(): AppCompatActivity() , HomeContract.HomeView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
    }

    private fun initView(binding: ActivityHomeBinding) {
        intent.getStringExtra("id")?.let {
            binding.homeIdSmallText.text = it
            binding.homeIdMediumText.text = it
        }
    }

    override fun testView() {

    }
}