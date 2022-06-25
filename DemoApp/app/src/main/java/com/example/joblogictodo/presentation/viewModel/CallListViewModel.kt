package com.example.joblogictodo.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.joblogictodo.Constants
import com.example.joblogictodo.presentation.utils.CoroutineContextProvider
import com.example.joblogictodo.domain.interactor.GetAllCustomer
import com.example.joblogictodo.domain.models.Customer
import com.example.joblogictodo.remote.networkService.BinService
import com.example.joblogictodo.ui.utils.UIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CallListViewModel @Inject constructor(private val getAllCustomer: GetAllCustomer, contextProvider: CoroutineContextProvider): BaseViewModel(contextProvider) {
    private val _listCustomer = MutableLiveData<UIModel<List<Customer>>>()
    val listCustomer: LiveData<UIModel<List<Customer>>> = _listCustomer

    var statusLoading: MutableLiveData<String> = MutableLiveData()

    override val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        if (exception is BinService.BinServiceException) {
            _listCustomer.postValue(UIModel.error(exception))
        } else {
            _listCustomer.postValue(
                UIModel.error(
                    BinService.BinServiceException(
                        exception,
                        exception.message,
                        Constants.ERROR_CODE_UNKNOWN
                    )
                )
            )
        }
    }

    fun loadCustomer() {
        launchCoroutineIO {
            getAllCustomer(isUseLocal = false).collect {
                _listCustomer.postValue(UIModel.success(it))
            }
        }
    }

    fun setStatusLoading(message: String) {
        statusLoading.value = message
    }
}