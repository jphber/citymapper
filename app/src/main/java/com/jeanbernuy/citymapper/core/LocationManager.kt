package com.jeanbernuy.citymapper.core

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.jeanbernuy.citymapper.BuildConfig.APPLICATION_ID
import com.jeanbernuy.citymapper.R
import com.jeanbernuy.citymapper.presentation.MainActivity

class LocationManager(private val activity: MainActivity) {
    
    fun requestLocationPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_COARSE_LOCATION)) {
            activity.showErrorWithAction(R.string.permission_rationale, android.R.string.ok) {
                startLocationPermissionRequest()
            }

        } else {
            startLocationPermissionRequest()
        }
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(activity,
            arrayOf(ACCESS_COARSE_LOCATION),
            AppConstants.REQUEST_CODE_LOCATION_PERMISSIONS)
    }

    fun onRequestPermissionResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == AppConstants.REQUEST_CODE_LOCATION_PERMISSIONS) {
            when (PERMISSION_GRANTED) {
                grantResults[0] -> activity.getLastLocation()
                else -> {
                    activity.showErrorWithAction(R.string.permission_denied, R.string.settings) {
                        activity.startActivity(Intent().apply {
                            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            data = Uri.fromParts(AppConstants.DATA_PACKAGE, APPLICATION_ID, null)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }
                }
            }
        }
    }
}