package com.amir.huma.view.ui.main

import android.app.UiModeManager
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.amir.huma.R

class MainActivity : FragmentActivity() {
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
        checkModeType()
        checkHardwareFeature()
    }

    private fun checkHardwareFeature() {
        // Check if the telephony hardware feature is available.
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
            Log.d("HardwareFeatureTest", "Device can make phone calls")
        }

// Check if android.hardware.touchscreen feature is available.
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)) {
            Log.d("HardwareFeatureTest", "Device has a touch screen.")
        }
    }

    private fun checkModeType() {
        val uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
        if (uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION) {
            Toast.makeText(application, "TV", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(application, "non-TV", Toast.LENGTH_SHORT).show()
        }
    }
}