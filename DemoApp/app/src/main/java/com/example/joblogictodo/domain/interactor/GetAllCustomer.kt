package com.example.joblogictodo.domain.interactor

import com.example.joblogictodo.domain.repository.CustomerRepository
import javax.inject.Inject

class GetAllCustomer @Inject constructor(private val customerRepository: CustomerRepository) {
    operator fun invoke(isUseLocal: Boolean) = customerRepository.getAll(isUseLocal)
}