package com.altimetrick.gpslog.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.altimetrick.gpslog.util.AppUtils

class DeviceStartServiceReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, data: Intent?) {
        context?.let {
            AppUtils.scheduleJob(it)
        }
    }

}