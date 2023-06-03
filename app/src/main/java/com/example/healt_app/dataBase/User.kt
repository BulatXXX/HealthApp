package com.example.healt_app.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null ,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "birth_date")
    val birthDate: String,
    @ColumnInfo(name = "weight")
    val weight: Double? = 0.0,
    @ColumnInfo(name = "height")
    val height: Int? = 0,
)
