package com.altimetrick.gpslog.util

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import com.altimetrick.gpslog.localservice.ApiJobService
import java.util.*

object AppUtils {

    val currentDateTime: Date
        get() = Calendar.getInstance().time

    fun scheduleJob(context: Context){
        val componentName = ComponentName(context, ApiJobService::class.java)
        val builder = JobInfo.Builder(0, componentName)
        builder.setMinimumLatency( 2 * 2000 )
        builder.setOverrideDeadline( 6 * 10000 )
        builder.setPeriodic(60*1000)
        builder.setPersisted(true)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)

        val scheduler = context.getSystemService(JobScheduler::class.java)
        scheduler.schedule(builder.build())
    }
}