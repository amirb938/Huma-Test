package com.amir.huma.view.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.amir.huma.view.fragment.MainFragment
import com.amir.huma.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
    }
}