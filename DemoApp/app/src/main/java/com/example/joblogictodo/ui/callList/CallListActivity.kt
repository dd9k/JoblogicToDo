package com.example.joblogictodo.ui.callList

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joblogictodo.R
import com.example.joblogictodo.ui.adapterList.CustomerAdapter
import com.example.joblogictodo.databinding.ActivityCallListBinding
import com.example.joblogictodo.presentation.viewModel.CallListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CallListActivity : AppCompatActivity() {

    @Inject
    lateinit var customerAdapter: CustomerAdapter

    val viewModel: CallListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.call_list_title)
        val binding: ActivityCallListBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_call_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initViewConfig(binding)
        loadData()
    }

    private fun loadData() {
        viewModel.setStatusLoading(getString(R.string.loading_message))
        viewModel.loadCustomer()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewConfig(binding: ActivityCallListBinding) {
        binding.rvCallProduct.adapter = customerAdapter
        binding.rvCallProduct.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.rvCallProduct.layoutManager = LinearLayoutManager(this)

        viewModel.listCustomer.observe(this) {
            it?.let { uiModel ->
                if (uiModel.isSuccess()) {
                    if (uiModel.data.isNullOrEmpty()) {
                        viewModel.setStatusLoading(getString(R.string.no_data_found))
                    } else {
                        viewModel.setStatusLoading(getString(R.string.success_message))
                        customerAdapter.dataList = uiModel.data ?: ArrayList()
                        customerAdapter.notifyDataSetChanged()
                    }
                } else {
                    viewModel.setStatusLoading(uiModel.getErrorMessage(this))
                }
            }
        }
    }
}