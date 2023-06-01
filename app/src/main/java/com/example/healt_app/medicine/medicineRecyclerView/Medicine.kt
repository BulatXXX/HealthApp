package com.example.healt_app.medicine.medicineRecyclerView

import android.os.Parcel
import android.os.Parcelable


data class Medicine(val id :Int,val name: String, val frequency: String, val time: String): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt() ,
        parcel.readString().toString() ,
        parcel.readString().toString() ,
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel , flags: Int) {
        parcel.writeInt(id)
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
