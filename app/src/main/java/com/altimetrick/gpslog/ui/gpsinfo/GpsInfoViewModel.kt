package com.altimetrick.gpslog.ui.gpsinfo

import android.app.Application
import androidx.lifecycle.LiveData
import com.altimetrick.gpslog.base.BaseViewModel
import com.altimetrick.gpslog.db.model.LocationData
import com.altimetrick.gpslog.db.repository.LocationRepository

class GpsInfoViewModel(val app: Application): BaseViewModel(app){

    fun getLocations(): LiveData<List<LocationData>>{
        val locationRepository = LocationRepository(app)
        return locationRepository.locations
    }

}