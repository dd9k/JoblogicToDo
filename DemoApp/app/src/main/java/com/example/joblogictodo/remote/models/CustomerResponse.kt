package com.example.joblogictodo.remote.models
import com.google.gson.annotations.SerializedName

class CustomerResponse {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("number")
    var number: String? = null

    constructor()

    constructor(id: Int?, name: String?, number: String?) {
        this.id = id
        this.name = name
        this.number = number
    }
}