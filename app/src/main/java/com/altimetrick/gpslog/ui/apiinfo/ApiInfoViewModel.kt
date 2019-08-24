package com.altimetrick.gpslog.ui.apiinfo

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.altimetrick.gpslog.base.BaseViewModel
import com.altimetrick.gpslog.db.model.ApiData
import com.altimetrick.gpslog.db.repository.LocationRepository

class ApiInfoViewModel(val app: Application) : BaseViewModel(app){

    var isAPILogAvailable = ObservableField<Boolean>(false)

    var apiLogsLiveData = MutableLiveData<List<ApiData>>()
    var apiLogsObservableList = ObservableArrayList<ApiData>()

    fun getApiLogs(){
        val locationRepository = LocationRepository(app)
        apiLogsLiveData.value = locationRepository.apiLogs.value
    }

    fun updateAPILogs(apiLogs: List<ApiData>?){
        apiLogs?.let { logs ->
            if (logs.isNotEmpty()){
                isAPILogAvailable.set(true)
            }
            apiLogsObservableList.clear()
            apiLogsObservableList.addAll(logs)
        }

    }

}