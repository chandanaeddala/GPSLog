package com.altimetrick.gpslog.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.altimetrick.gpslog.BR
import com.altimetrick.gpslog.R
import com.altimetrick.gpslog.base.BaseActivity
import com.altimetrick.gpslog.databinding.ActivityHomeBinding
import com.altimetrick.gpslog.localservice.LocationService
import com.altimetrick.gpslog.ui.apiinfo.ApiInfoFragment
import com.altimetrick.gpslog.ui.gpsinfo.GpsInfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), BottomNavigationView.OnNavigationItemSelectedListener{


    override fun getContentView(): Int = R.layout.activity_home

    override fun getViewModel(): HomeViewModel? = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    override fun getBindingVariable(): Int = BR.homeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            this@HomeActivity.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123)
        }else{
            startService(Intent(this@HomeActivity, LocationService::class.java))
        }

        bottomNavigation.setOnNavigationItemSelectedListener(this)
        bottomNavigation.selectedItemId = R.id.menuWebServiceLog
    }

    fun updateTitle(title: String){
        supportActionBar?.title = title
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123){
            if (grantResults [0] == PackageManager.PERMISSION_GRANTED){
                startService(Intent(this@HomeActivity, LocationService::class.java))
            }else{
                showInfoAlert("To access your location update we required you to accept the permission " +
                        "for accessing location from device. Kindly go to settings and allow " +
                        "location permission access to continue with this app.")
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuWebServiceLog ->  pushFragment(ApiInfoFragment(), false,  ApiInfoFragment::class.java.name)
            R.id.menuGpsLog ->  pushFragment(GpsInfoFragment(), false, GpsInfoFragment::class.java.name)
        }
        return true
    }

}