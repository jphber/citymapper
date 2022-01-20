package com.jeanbernuy.citymapper.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import com.jeanbernuy.citymapper.R
import com.jeanbernuy.citymapper.core.AppConstants
import com.jeanbernuy.citymapper.core.LocationHelper
import com.jeanbernuy.citymapper.core.LocationManager
import com.jeanbernuy.citymapper.presentation.ui.NearbyStationFragmentArgs

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var locationManager: LocationManager
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationManager = LocationManager(this)
        locationManager.requestLocationPermissions()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationManager.onRequestPermissionResult(requestCode, grantResults)
    }

    internal fun showErrorWithAction(errorMessage: Int, errorCta: Int, ctaClick: () -> Unit) {
        Snackbar.make(findViewById(android.R.id.content), errorMessage, LENGTH_INDEFINITE).apply {
            setAction(errorCta) { ctaClick() }
            show()
        }
    }

    @SuppressLint("MissingPermission")
    internal fun getLastLocation() {
        fusedLocationProviderClient.lastLocation
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && task.result != null) {
                    onLocationSuccess(task.result.longitude, task.result.latitude)
                } else {
                    Log.w("MainActivity", "getLastLocation:exception", task.exception)
                }
            }
    }

    private fun onLocationSuccess(longitude: Double, latitude: Double) {
        if (LocationHelper.isLondon(longitude, latitude)) {
            navController.setGraph(R.navigation.nav_graph,
                NearbyStationFragmentArgs(latitude.toFloat(), longitude.toFloat()).toBundle())
            setupActionBarWithNavController(navController)
        } else {
            navController.setGraph(R.navigation.nav_graph,
                NearbyStationFragmentArgs(AppConstants.LATITUDE.toFloat(),
                    AppConstants.LONGITUDE.toFloat()).toBundle())
            setupActionBarWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}