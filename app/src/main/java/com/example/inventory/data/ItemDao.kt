package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao//makes the interface the Data Access Object in the app create a persistence layer from the rest of the app, this  isolation follows the single-responsibility principle
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)//this makes sure any conflict is ignored by telling room the strategy when a new item is entered
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    //queries are run on the background thread you don't explicitly have to make fun a suspend fun
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}