 

package com.code.todo.data
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity data class represents a single row in the database.
 */


@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val task: String,

)
