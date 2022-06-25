package com.example.joblogictodo.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.joblogictodo.local.models.CustomerEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class CustomerEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "number")
    var number: String? = null

    constructor()

    constructor(id: Int?, name: String?, number: String?) {
        this.id = id
        this.name = name
        this.number = number
    }

    companion object {
        const val TABLE_NAME = "Customer"
    }
}