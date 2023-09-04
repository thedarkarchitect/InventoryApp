package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface ItemsRepository{
    //retrieve all items from the given database
    fun getAllItemsStream(): Flow<List<Item>>

    //retrieve an item from the given data source
    fun getItemStream(id: Int): Flow<Item?>

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun updateItem(item: Item)

}
