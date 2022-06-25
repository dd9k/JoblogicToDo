package com.example.joblogictodo.domain.interactor

import com.example.joblogictodo.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllProduct @Inject constructor(private val productRepository: ProductRepository) {
    operator fun invoke(isUseLocal: Boolean) = productRepository.getAll(isUseLocal)
}