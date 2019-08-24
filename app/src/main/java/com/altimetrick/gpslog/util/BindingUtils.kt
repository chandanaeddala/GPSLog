package com.altimetrick.gpslog.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.altimetrick.gpslog.db.model.ApiData
import com.altimetrick.gpslog.ui.apiinfo.adapter.ApiLogAdapter

@BindingAdapter("apiLogs")
fun addTransactions(recyclerView: RecyclerView, apiLogs: ArrayList<ApiData>){
    recyclerView.let { it ->
        it.adapter?.also { ta ->
            val adapter = ta as ApiLogAdapter
            adapter.refreshApiLogs(apiLogs)
        }
    }
}