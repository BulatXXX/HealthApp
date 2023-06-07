package com.example.healt_app.dataBase

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.FileDescriptor

@Entity(tableName = "diseases")
data class Disease(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "doctor_id")
    val doctorId: Int,
    @ColumnInfo(name = "doctor_name")
    val doctorName: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "patient_id")
    val patientId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int ,
        parcel.readString().toString() ,
        parcel.readInt() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel , flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeInt(doctorId)
        parcel.writeString(doctorName)
        parcel.writeString(description)
        parcel.writeInt(patientId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Disease> {
        override fun createFromParcel(parcel: Parcel): Disease {
            return Disease(parcel)
        }

        override fun newArray(size: Int): Array<Disease?> {
            return arrayOfNulls(size)
        }
    }
}
