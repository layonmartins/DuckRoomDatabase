package com.layon.duckroomdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val tableName = "duck_table"

@Entity(tableName = tableName)
data class Duck(
    @PrimaryKey
    val name: String,
    val age: Int
)