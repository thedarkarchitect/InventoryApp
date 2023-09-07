package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "items")//sets the string as the SQLite table name
class Item(
    @PrimaryKey(autoGenerate = true)//this allows the id property to uniquely identify every record/entry in your Item table
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
) {
    fun copy(quantity: Int): Item {
        return Item(id, name, price, quantity)
    }
}
