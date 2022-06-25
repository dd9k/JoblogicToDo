package com.example.joblogictodo.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.joblogictodo.R
import com.example.joblogictodo.databinding.ActivityHomeBinding
import com.example.joblogictodo.presentation.viewModel.HomeViewModel
import com.example.joblogictodo.ui.buyList.BuyListActivity
import com.example.joblogictodo.ui.callList.CallListActivity
import com.example.joblogictodo.ui.sellList.SellListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initViewConfig(binding)
        loadData()
    }

    private fun loadData() {
        viewModel.initDummyStored()
    }

    private fun initViewConfig(binding: ActivityHomeBinding) {
        binding.btnCallList.setOnClickListener {
            startActivity(Intent(this, CallListActivity::class.java))
        }
        binding.btnBuyList.setOnClickListener {
            startActivity(Intent(this, BuyListActivity::class.java))
        }
        binding.btnSellList.setOnClickListener {
            startActivity(Intent(this, SellListActivity::class.java))
        }
    }
}