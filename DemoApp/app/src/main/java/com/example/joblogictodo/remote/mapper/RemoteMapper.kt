package com.example.joblogictodo.remote.mapper

interface RemoteMapper<M, E> {
    fun mapFromResponse(type: M): E
}
