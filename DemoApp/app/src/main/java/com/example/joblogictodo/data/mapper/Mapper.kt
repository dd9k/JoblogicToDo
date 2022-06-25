package com.example.joblogictodo.data.mapper

interface Mapper<E, D> {

    fun mapFromSource(type: E): D

    fun mapToSource(type: D): E
}
