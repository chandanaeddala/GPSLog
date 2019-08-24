package com.altimetrick.gpslog.db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.altimetrick.gpslog.db.dao.DaoAccess
import com.altimetrick.gpslog.db.model.ApiData
import com.altimetrick.gpslog.db.model.LocationData

@Database(entities = [LocationData::class, ApiData::class], version = 2, exportSchema = false)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess
}