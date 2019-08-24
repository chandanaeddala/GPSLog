package com.altimetrick.gpslog.ui.apiinfo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.altimetrick.gpslog.BR
import com.altimetrick.gpslog.R
import com.altimetrick.gpslog.base.BaseFragment
import com.altimetrick.gpslog.databinding.FragmentApiLogsBinding
import com.altimetrick.gpslog.ui.apiinfo.adapter.ApiLogAdapter
import kotlinx.android.synthetic.main.fragment_api_logs.*

class ApiInfoFragment: BaseFragment<FragmentApiLogsBinding, ApiInfoViewModel>() {

    override fun getViewModel(): ApiInfoViewModel? = ViewModelProviders.of(this).get(ApiInfoViewModel::class.java)

    override fun getBindingVariable(): Int = BR.apiLogVM

    override fun getContentView(): Int = R.layout.fragment_api_logs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAPILogs.layoutManager = LinearLayoutManager(activity)
        recyclerAPILogs.setHasFixedSize(true)
        recyclerAPILogs.adapter = ApiLogAdapter()

        getViewModel()?.apiLogsLiveData?.observe(this, Observer {
            getViewModel()?.updateAPILogs(it)
        })
        getViewModel()?.getApiLogs()
    }

}