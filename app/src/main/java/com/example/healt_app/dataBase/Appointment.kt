package com.example.healt_app.dataBase

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "doctor_id")
    val doctorId: Int,
    @ColumnInfo(name = "patient_id")
    val patientId: Int,
    @ColumnInfo(name = "doctor_name")
    val doctorName: String,
    @ColumnInfo(name = "patient_name")
    val patientName: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "post")
    val post: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int ,
        parcel.readInt() ,
        parcel.readInt() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel , flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(doctorId)
        parcel.writeInt(patientId)
        parcel.writeString(doctorName)
        parcel.writeString(patientName)
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(post)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Appointment> {
        override fun createFromParcel(parcel: Parcel): Appointment {
            return Appointment(parcel)
        }

        override fun newArray(size: Int): Array<Appointment?> {
            return arrayOfNulls(size)
        }
    }
}
