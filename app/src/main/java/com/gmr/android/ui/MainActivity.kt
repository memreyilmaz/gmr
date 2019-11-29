package com.gmr.android.ui

import android.os.Bundle
import com.gmr.android.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isTablet = resources.getBoolean(R.bool.isTablet)

        if (isTablet) setContentView(R.layout.activity_main_tablet)
        else setContentView(R.layout.activity_main)
    }
}
