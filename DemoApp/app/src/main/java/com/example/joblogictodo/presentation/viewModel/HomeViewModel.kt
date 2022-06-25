package com.example.joblogictodo.presentation.viewModel

import android.util.Log
import com.example.joblogictodo.domain.interactor.InsertProduct
import com.example.joblogictodo.domain.models.Product
import com.example.joblogictodo.presentation.utils.CoroutineContextProvider
import com.example.joblogictodo.presentation.utils.PresentationPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val preferencesHelper: PresentationPreferencesHelper, private val insertProduct: InsertProduct, contextProvider: CoroutineContextProvider): BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("coroutineException", exception.message ?: "no message")
    }

    fun initDummyStored() {
        if (!preferencesHelper.isDoneDummy) {
            launchCoroutineIO {
                val listDummy = listOf(
                    Product(1, "iPhone X", 150000, 1, 2),
                    Product(2, "TV", 38000, 2, 2),
                    Product(3, "Table", 12000, 1, 2)
                )
                insertProduct.invoke(listDummy)
                preferencesHelper.isDoneDummy = true
            }
        }
    }
}