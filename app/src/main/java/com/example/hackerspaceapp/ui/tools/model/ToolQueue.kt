package com.example.hackerspaceapp.ui.tools.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat

@Entity
@IgnoreExtraProperties
data class ToolQueue(
       @PrimaryKey(autoGenerate = true)
       var id:Int,

       @ColumnInfo(name="toolID")
       var toolID:Int,

       @ColumnInfo(name="date")
       var date:String,

       @ColumnInfo(name="timeID")
       var timeID:Int,

       @ColumnInfo(name="isReserved")
       var isReserved:Boolean
) {
       constructor(toolID: Int,date:String,timeID: Int):this(0,toolID,date,timeID,false){

       }

       @Exclude
       fun toMap():Map<String,Any?>{
              return mapOf(
                     "id" to id,
                     "toolID" to toolID,
                     "date" to date,
                     "timeID" to timeID,
                     "isReserved" to isReserved
              )
       }

}