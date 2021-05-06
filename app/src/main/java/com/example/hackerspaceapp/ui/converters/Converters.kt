package com.example.hackerspaceapp.ui.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.sql.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value:Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date:Date?):Long?{
        return date?.time?.toLong()
    }
}