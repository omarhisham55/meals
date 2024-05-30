package com.example.data.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {
    @TypeConverter
    fun fromAnyToString(attribute: Any?): String {
        attribute?.let {
            return attribute.toString()
        }
        return ""
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?): Any {
        attribute?.let {
            return attribute
        }
        return ""
    }
}