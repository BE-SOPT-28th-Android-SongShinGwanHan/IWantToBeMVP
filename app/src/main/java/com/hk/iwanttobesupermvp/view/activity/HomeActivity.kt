package com.hk.iwanttobesupermvp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hk.iwanttobesupermvp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
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
}