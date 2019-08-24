package com.altimetrick.gpslog.localservice

import android.app.job.JobParameters
import android.app.job.JobService
import com.altimetrick.gpslog.db.repository.LocationRepository
import com.altimetrick.gpslog.util.AppUtils

class ApiJobService : JobService() {

    private var locationRepository: LocationRepository?= null

    override fun onStopJob(params: JobParameters?): Boolean {

        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        // You can create API logic here
        // Imagine any rss api called from here and you can log into database

        locationRepository = LocationRepository(applicationContext)
        locationRepository?.insertApiLog()

        AppUtils.scheduleJob(applicationContext)
        return true
    }

}