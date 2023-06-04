package com.example.healt_app.dataBase

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "medicine_name")
    val name: String ,
    @ColumnInfo(name = "freq")
    val frequency: String ,
    @ColumnInfo(name = "mtime")
    val time: String ,
    @ColumnInfo(name = "patientId")
    val patientId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel , flags: Int) {
        id?.let { parcel.writeInt(it) }
        parcel.writeString(name)
        parcel.writeString(frequency)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicine> {
        override fun createFromParcel(parcel: Parcel): Medicine {
            return Medicine(parcel)
        }

        override fun newArray(size: Int): Array<Medicine?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "$id $name $frequency $time"
    }

}
