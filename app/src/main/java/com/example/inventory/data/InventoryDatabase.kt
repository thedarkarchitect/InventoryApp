package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase: RoomDatabase(){//this acts as a database holder. class is abstract because room creates the implementation for us
    abstract fun itemDao(): ItemDao //this lets database know about the DAO

    companion object {//allows access to methods to create or get the database and uses the class name as the qualifier

        @Volatile//makes value of the variable never cached, all reads and writes are to and from the main memory
        private var Instance: InventoryDatabase? = null//this initializes the database

        fun getDatabase(context: Context): InventoryDatabase{//
            return Instance ?: synchronized(this){ //synchronized block means that only one thread of execution at a time can enter this block of code, which makes the database only get initializeed once
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
//                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it}//keeps a reference to the recently created db instance
            }
        }
    }
}