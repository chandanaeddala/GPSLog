package com.altimetrick.gpslog.ui.apiinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.altimetrick.gpslog.databinding.ViewHolderApiLogBinding
import com.altimetrick.gpslog.db.model.ApiData

class ApiLogAdapter: RecyclerView.Adapter<ApiLogAdapter.ApiLogViewHolder>() {

    var apiLogs = ArrayList<ApiData>()

    fun refreshApiLogs(apiDatas: List<ApiData>){
        apiLogs.clear()
        apiLogs.addAll(apiDatas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiLogViewHolder {
        return ApiLogViewHolder(ViewHolderApiLogBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = apiLogs.size

    override fun onBindViewHolder(holder: ApiLogViewHolder, position: Int) {
        holder.onBind(apiLogs[position])
    }

    inner class ApiLogViewHolder(val binding: ViewHolderApiLogBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(apiLog: ApiData){
            binding.apiLog = apiLog
            binding.executePendingBindings()
        }
    }
}