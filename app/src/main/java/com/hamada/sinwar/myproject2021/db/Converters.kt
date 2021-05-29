package com.hamada.sinwar.myproject2021.db

import androidx.room.TypeConverter
import com.hamada.sinwar.myproject2021.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}