package com.example.hackerspaceapp.ui.projects.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Project(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name="name")
    var projname: String?,

    @ColumnInfo(name="info")
    var projinfo: String?,

    @ColumnInfo(name="doc")//date of creation
    var doc:Long
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(projname)
        parcel.writeString(projinfo)
        parcel.writeLong(doc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Project> {
        override fun createFromParcel(parcel: Parcel): Project {
            return Project(parcel)
        }

        override fun newArray(size: Int): Array<Project?> {
            return arrayOfNulls(size)
        }
    }

}