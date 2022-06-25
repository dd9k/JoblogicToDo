package com.example.joblogictodo.remote.models
import com.google.gson.annotations.SerializedName

class ProductResponse {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("price")
    var price: Int? = null

    @SerializedName("quantity")
    var quantity: Int? = null

    @SerializedName("type")
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