package com.example.joblogictodo.ui.sellList

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joblogictodo.R
import com.example.joblogictodo.ui.adapterList.ProductAdapter
import com.example.joblogictodo.databinding.ActivitySellListBinding
import com.example.joblogictodo.presentation.viewModel.SellListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellListActivity : AppCompatActivity() {

    @Inject
    lateinit var productAdapter: ProductAdapter

    val viewModel: SellListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.sell_list_title)
        val binding: ActivitySellListBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_sell_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initViewConfig(binding)
        loadData()
    }

    private fun loadData() {
        viewModel.setStatusLoading(getString(R.string.loading_message))
        viewModel.loadProduct()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewConfig(binding: ActivitySellListBinding) {
        binding.rvSellProduct.adapter = productAdapter
        binding.rvSellProduct.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.rvSellProduct.layoutManager = LinearLayoutManager(this)

        viewModel.listProduct.observe(this) {
            it?.let { uiModel ->
                if (uiModel.isSuccess()) {
                    if (uiModel.data.isNullOrEmpty()) {
                        viewModel.setStatusLoading(getString(R.string.no_data_found))
                    } else {
                        viewModel.setStatusLoading(getString(R.string.success_message))
                        productAdapter.dataList = uiModel.data ?: ArrayList()
                        productAdapter.notifyDataSetChanged()
                    }
                } else {
                    viewModel.setStatusLoading(uiModel.getErrorMessage(this))
                }
            }
        }
    }
}