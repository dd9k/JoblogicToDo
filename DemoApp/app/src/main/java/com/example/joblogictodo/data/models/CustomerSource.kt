package com.example.joblogictodo.data.models

class CustomerSource {
    var id: Int? = null

    var name: String? = null

    var number: String? = null

    constructor()

    constructor(id: Int?, name: String?, number: String?) {
        this.id = id
        this.name = name
        this.number = number
    }
}