package com.example.healt_app.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null ,
    @ColumnInfo(name = "login")
    val login: String ,
    @ColumnInfo(name = "password")
    val password: String ,

    @ColumnInfo(name = "name")
    val name: String ,
    @ColumnInfo(name = "birth_date")
    val birthDate: String ,

    @ColumnInfo(name = "roll")
    val roll: Boolean ,

    @ColumnInfo(name = "post")
    val post: String? = null ,

    @ColumnInfo(name = "weight")
    val weight: Double? = null ,
    @ColumnInfo(name = "height")
    val height: Int? = null ,
    )

{

}
