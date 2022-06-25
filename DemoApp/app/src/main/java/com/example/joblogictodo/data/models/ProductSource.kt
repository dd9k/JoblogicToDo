package com.example.joblogictodo.data.models

class ProductSource {
    var id: Int? = null

    var name: String? = null

    var price: Int? = null

    var quantity: Int? = null

    var type: Int? = null

    constructor()

    constructor(id: Int?, name: String?, price: Int?, quantity: Int?, type: Int?) {
        this.id = id
        this.name = name
        this.price = price
        this.quantity = quantity
        this.type = type
    }
}