package com.example.healt_app

import android.content.Context
import com.example.healt_app.dataBase.MainDB
import java.time.LocalDate
import java.time.Period

class Checker {
    fun checkAge(birthDate: LocalDate): Boolean {
        return !(Period.between(birthDate, LocalDate.now()).isNegative || Period.between(birthDate, LocalDate.now()).isZero)
    }
    fun checkAge(birthDate: String): Boolean {
        val birth_date = LocalDate.of(
            birthDate.substring(6).toInt() ,
            birthDate.substring(3 , 5).toInt() ,
            birthDate.substring(0 , 2).toInt()
        )
        return !(Period.between(birth_date, LocalDate.now()).isNegative || Period.between(birth_date, LocalDate.now()).isZero)
    }

}