package com.example.joblogictodo.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.joblogictodo.local.models.ProductEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class ProductEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "price")
    var price: Int? = null

    @ColumnInfo(name = "quantity")
    var quantity: Int? = null

    @ColumnInfo(name = "type")
    var type: Int? = null

    constructor()

    constructor(id: Int?, name: String?, price: Int?, quantity: Int?, type: Int?) {
        this.id = id
        this.name = name
        this.price = price
        this.quantity = quantity
        this.type = type
    }

    companion object {
        const val TABLE_NAME = "ItemToSell"
    }
}