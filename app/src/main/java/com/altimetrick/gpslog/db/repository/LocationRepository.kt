package com.altimetrick.gpslog.db.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.altimetrick.gpslog.db.db.LocationDatabase
import com.altimetrick.gpslog.db.model.ApiData
import com.altimetrick.gpslog.db.model.LocationData
import com.altimetrick.gpslog.util.AppUtils

class LocationRepository(context: Context) {

    private val DB_NAME = "LocationDB"

    private val locationDatabase: LocationDatabase

    val locations: LiveData<List<LocationData>>
        get() = locationDatabase.daoAccess().fetchAllLocations()

    val apiLogs: LiveData<List<ApiData>>
        get() = locationDatabase.daoAccess().fetchAllApiLogs()

    init {
        locationDatabase = Room.databaseBuilder(context, LocationDatabase::class.java, DB_NAME).build()
    }

    fun insertTask(latitude: String, longtitude: String) {
        val location = LocationData()
        location.latitude = latitude
        location.longtitude = longtitude
        location.createdAt = (AppUtils.currentDateTime)

        insertTask(location)
    }

    @SuppressLint("StaticFieldLeak")
    private fun insertTask(location: LocationData) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                locationDatabase.daoAccess().insertLocation(location)
                return null
            }
        }.execute()
    }

    fun insertApiLog() {
        val apiData = ApiData()
        apiData.createdAt = (AppUtils.currentDateTime)

        insertApiLog(apiData)
    }

    @SuppressLint("StaticFieldLeak")
    private fun insertApiLog(apiData: ApiData) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                locationDatabase.daoAccess().insertApiLog(apiData)
                return null
            }
        }.execute()
    }

}