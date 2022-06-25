package com.example.joblogictodo.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.joblogictodo.Constants
import com.example.joblogictodo.presentation.utils.CoroutineContextProvider
import com.example.joblogictodo.domain.interactor.GetAllProduct
import com.example.joblogictodo.domain.interactor.InsertProduct
import com.example.joblogictodo.domain.models.Product
import com.example.joblogictodo.remote.networkService.BinService
import com.example.joblogictodo.ui.utils.UIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class BuyListViewModel @Inject constructor(private val getAllProduct: GetAllProduct, contextProvider: CoroutineContextProvider) :
    BaseViewModel(contextProvider) {

    private val _listProduct = MutableLiveData<UIModel<List<Product>>>()
    val listProduct: LiveData<UIModel<List<Product>>> = _listProduct

    var statusLoading: MutableLiveData<String> = MutableLiveData()

    override val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        if (exception is BinService.BinServiceException) {
            _listProduct.postValue(UIModel.error(exception))
        } else {
            _listProduct.postValue(
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

    fun loadProduct() {
        launchCoroutineIO {
            getAllProduct(isUseLocal = false).collect {
                _listProduct.postValue(UIModel.success(it))
            }
        }
    }

    fun setStatusLoading(message: String) {
        statusLoading.value = message
    }
}