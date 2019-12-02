package com.gmr.android.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.gmr.android.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isTablet = resources.getBoolean(R.bool.isTablet)

        if (isTablet){
            setContentView(R.layout.activity_main_tablet)
            setSupportActionBar(main_activity_toolbar)
        } else {
            setContentView(R.layout.activity_main)
            setSupportActionBar(main_activity_toolbar)
            NavigationUI.setupActionBarWithNavController(
                this, findNavController(R.id.navHostFragment))
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostFragment).navigateUp()
    }
}
