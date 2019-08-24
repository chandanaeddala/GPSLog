package com.altimetrick.gpslog.ui.gpsinfo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.altimetrick.gpslog.BR
import com.altimetrick.gpslog.R
import com.altimetrick.gpslog.base.BaseFragment
import com.altimetrick.gpslog.databinding.FragmentGpsInfoBinding
import com.altimetrick.gpslog.ui.home.HomeActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class GpsInfoFragment : BaseFragment<FragmentGpsInfoBinding, GpsInfoViewModel>(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    override fun getViewModel(): GpsInfoViewModel? = ViewModelProviders.of(this).get(GpsInfoViewModel::class.java)

    override fun getBindingVariable(): Int = BR.gpsInfoVM

    override fun getContentView(): Int = R.layout.fragment_gps_info

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity).updateTitle("Gps Info")

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map
        getViewModel()?.getLocations()?.observe(this, Observer {
            it?.let { locationsList ->
                for (location in locationsList) {
                    if (location.latitude != null && location.longtitude != null) {
                        val markerLocation = LatLng(location.latitude!!.toDouble(), location.longtitude!!.toDouble())
                        mMap?.addMarker(MarkerOptions().position(markerLocation).title(location.id.toString()))

                        if (locationsList.indexOf(location) == 0) {
                            val yourLocation = CameraUpdateFactory.newLatLngZoom(markerLocation, 25f)
                            mMap?.animateCamera(yourLocation)
                        }

                    }
                }
            }

        })
    }

}