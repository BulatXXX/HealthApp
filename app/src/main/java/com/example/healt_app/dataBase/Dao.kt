package com.example.healt_app.dataBase

import androidx.room.*
import androidx.room.Dao

import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    /**
     *   User
     */
    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteUserById(id: Int)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserByID(id : Int) : Flow<User>

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM users WHERE login = :login AND password = :password")
    fun getUser(login: String, password: String): Flow<User>

    @Query("SELECT * FROM users WHERE login = :login ")
    fun getUser(login: String): Flow<User>




}