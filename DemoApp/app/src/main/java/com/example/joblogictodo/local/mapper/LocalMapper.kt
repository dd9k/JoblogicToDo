package com.example.joblogictodo.local.mapper

interface LocalMapper<T, V> {
    fun mapFromEntity(type: T): V

    fun mapToEntity(type: V): T
}
