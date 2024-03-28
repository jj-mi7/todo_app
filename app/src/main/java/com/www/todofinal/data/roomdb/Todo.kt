package com.www.todofinal.data.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val note: String,
    val done: Boolean,
    val dateTime: String = "",
    val color: Int,
    val category: String,
    val progress: Float,
    val priority: Int
)