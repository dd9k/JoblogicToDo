package com.example.joblogictodo.di

import android.app.Application
import com.example.joblogictodo.data.repository.CustomerLocal
import com.example.joblogictodo.data.repository.ProductLocal
import com.example.joblogictodo.local.dao.CustomerDAO
import com.example.joblogictodo.local.dao.ProductDAO
import com.example.joblogictodo.local.database.MyDataBase
import com.example.joblogictodo.local.repository.CustomerLocalImp
import com.example.joblogictodo.local.repository.ProductLocalImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): MyDataBase {
        return MyDataBase.getDatabase(application)
    }

    @Singleton
    @Provides
    fun provideCustomerDAO(myDataBase: MyDataBase): CustomerDAO {
        return myDataBase.customerDAO()
    }

    @Singleton
    @Provides
    fun provideProductDAO(myDataBase: MyDataBase): ProductDAO {
        return myDataBase.productDAO()
    }

    @Provides
    @Singleton
    fun provideCustomerLocal(customerLocal: CustomerLocalImp): CustomerLocal {
        return customerLocal
    }

    @Provides
    @Singleton
    fun provideProductLocal(productLocal: ProductLocalImp): ProductLocal {
        return productLocal
    }
}
