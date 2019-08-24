package com.altimetrick.gpslog.localservice

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.altimetrick.gpslog.db.repository.LocationRepository
import com.altimetrick.gpslog.util.LocationUtil
import com.google.android.gms.location.LocationRequest


class LocationService: IntentService("LocationUpdate"), LocationListener {

    private var isGpsEnabled = false
    private var isNetworkEnabled = false

    private var locationManager: LocationManager ?= null
    private var locationRequest: LocationRequest?= null

    private var locationRepository: LocationRepository?= null

    override fun onHandleIntent(intent: Intent?) {
        locationRepository = LocationRepository(applicationContext)
        val mainThread = Handler(Looper.getMainLooper())
        mainThread.post {
            locationRequest = LocationRequest.create()
            locationRequest?.interval =
                LocationUtil.UPDATE_INTERVAL_IN_MILLISECONDS
            locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest?.fastestInterval =
                LocationUtil.FAST_INTERVAL_CEILING_IN_MILLISECONDS

            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    fun getLocation(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager?.let {manager ->
            try {
                isGpsEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                isNetworkEnabled = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            } catch (ex: Exception) {
                // Catch exception here
            }
            var lastKnownLocation: Location ?= null
            if (isGpsEnabled){
                locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000L, 0.0F, this@LocationService)
                lastKnownLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }else if (isNetworkEnabled){
                locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 0f, this)
                lastKnownLocation = locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }
            locationRepository?.insertTask(lastKnownLocation?.latitude.toString(), lastKnownLocation?.longitude.toString())
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }


    override fun onLocationChanged(location: Location?) {
        Log.d("Location", "Location is ${location?.latitude} and ${location?.longitude}")
        locationRepository?.insertTask(location?.latitude.toString(), location?.longitude.toString())
    }

}