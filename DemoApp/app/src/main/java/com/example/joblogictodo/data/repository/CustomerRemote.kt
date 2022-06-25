package com.example.joblogictodo.data.repository

import com.example.joblogictodo.data.models.CustomerSource
import kotlinx.coroutines.flow.Flow

interface CustomerRemote {
    fun getAll(): Flow<List<CustomerSource>>
}