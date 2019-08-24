package com.altimetrick.gpslog.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.altimetrick.gpslog.db.model.ApiData
import com.altimetrick.gpslog.db.model.LocationData

@Dao
interface DaoAccess {

    @Insert
    fun insertLocation(location: LocationData): Long?

    @Insert
    fun insertApiLog(apiData: ApiData): Long?

    @Query("SELECT * FROM LocationData ORDER BY created_at desc")
    fun fetchAllLocations(): LiveData<List<LocationData>>

    @Query("SELECT * FROM ApiData ORDER BY created_at desc")
    fun fetchAllApiLogs(): LiveData<List<ApiData>>
}
