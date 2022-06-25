package com.example.joblogictodo.di

import com.example.joblogictodo.data.CustomerRepositoryImp
import com.example.joblogictodo.data.ProductRepositoryImp
import com.example.joblogictodo.domain.repository.CustomerRepository
import com.example.joblogictodo.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCustomerRepository(customerRepository: CustomerRepositoryImp): CustomerRepository = customerRepository

    @Provides
    @Singleton
    fun provideProductRepository(productRepository: ProductRepositoryImp): ProductRepository = productRepository
}
