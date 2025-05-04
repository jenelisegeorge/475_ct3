package com.code.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ToDo.data.Item
import com.example.ToDo.data.ItemDao

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ToDoDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}