package com.altimetrick.gpslog.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.roomdb.utils.TimestampConverter
import java.util.*


@Entity
class LocationData {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var latitude: String? = null
    var longtitude: String? = null

    @ColumnInfo(name = "created_at")
    @TypeConverters(TimestampConverter::class)
    var createdAt: Date? = null
}