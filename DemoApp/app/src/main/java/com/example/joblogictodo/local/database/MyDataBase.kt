package com.example.joblogictodo.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.joblogictodo.domain.models.Customer
import com.example.joblogictodo.domain.models.Product
import com.example.joblogictodo.local.database.MyDataBase.Companion.DATABASE_VERSION
import com.example.joblogictodo.local.dao.CustomerDAO
import com.example.joblogictodo.local.dao.ProductDAO
import com.example.joblogictodo.local.models.CustomerEntity
import com.example.joblogictodo.local.models.ProductEntity


@Database(
    entities = [
        CustomerEntity::class,
        ProductEntity::class],
    version = DATABASE_VERSION)
abstract class MyDataBase : RoomDatabase() {

    abstract fun customerDAO(): CustomerDAO

    abstract fun productDAO() : ProductDAO

    suspend fun clearData() {
        customerDAO().deleteAll()
        productDAO().deleteAll()
    }

    companion object {
        private var INSTANCE: MyDataBase? = null
        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "my-database"

        fun getDatabase(context: Context): MyDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, MyDataBase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}