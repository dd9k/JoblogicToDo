package com.example.joblogictodo.domain.interactor

import com.example.joblogictodo.domain.models.Product
import com.example.joblogictodo.domain.repository.ProductRepository
import javax.inject.Inject

class InsertProduct @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke(listProduct: List<Product>) = productRepository.insert(listProduct)
}