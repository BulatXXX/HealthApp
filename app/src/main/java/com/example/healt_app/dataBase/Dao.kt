package com.example.healt_app.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>
}