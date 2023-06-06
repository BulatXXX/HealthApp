package com.example.healt_app.dataBase

import android.os.Parcel
import android.os.Parcelable
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
    var name: String ,
    @ColumnInfo(name = "birth_date")
    var birthDate: String ,

    @ColumnInfo(name = "roll")
    val roll: Boolean ,

    @ColumnInfo(name = "post")
    var post: String? = "" ,

    @ColumnInfo(name = "weight")
    var weight: Double? = 0.0 ,
    @ColumnInfo(name = "height")
    var height: Int? = 0 ,
    )

    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readByte() != 0.toByte() ,
        parcel.readString() ,
        parcel.readValue(Double::class.java.classLoader) as? Double ,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel , flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(login)
        parcel.writeString(password)
        parcel.writeString(name)
        parcel.writeString(birthDate)
        parcel.writeByte(if (roll) 1 else 0)
        parcel.writeString(post)
        parcel.writeValue(weight)
        parcel.writeValue(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}
