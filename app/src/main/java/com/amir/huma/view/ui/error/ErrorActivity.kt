package com.amir.huma.view.ui.error

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.amir.huma.R

class ErrorActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.error_fragment, ErrorViewFragment())
                .commitNow()
        }
    }
}