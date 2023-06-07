package com.example.healt_app.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [User::class,Medicine::class,Appointment::class,Disease::class], version = 1)
abstract class MainDB : RoomDatabase() {
    abstract fun getDao():Dao
    companion object {
        fun getDb(context: Context): MainDB {
            return Room.databaseBuilder(
                context.applicationContext ,
                MainDB::class.java ,
                "health_app_test4.db"
            ).build()
        }

    }
}