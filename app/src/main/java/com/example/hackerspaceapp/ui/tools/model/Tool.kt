package com.example.hackerspaceapp.ui.tools.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tool(
        @PrimaryKey(autoGenerate = true)
        var id:Int,

        @ColumnInfo(name = "name")
        var name:String,

        @ColumnInfo(name = "image")
        var image:Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(image)
    }



    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tool> {
        override fun createFromParcel(parcel: Parcel): Tool {
            return Tool(parcel)
        }

        override fun newArray(size: Int): Array<Tool?> {
            return arrayOfNulls(size)
        }
    }

    constructor(name:String,image: Int):this(0,name,image){
        this.name=name
        this.image=image
    }
}