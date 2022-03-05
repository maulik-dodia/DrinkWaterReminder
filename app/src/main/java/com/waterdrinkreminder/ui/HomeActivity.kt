package com.waterdrinkreminder.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.waterdrinkreminder.R
import com.waterdrinkreminder.databinding.ActivityHomeBinding
import com.waterdrinkreminder.viewmodel.SplashViewModel

class HomeActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    private lateinit var dataBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashViewModel.isLoading.value
            }
        }
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        dataBinding.apply {
            bottomNavView.apply {
                /*background = null
                menu.getItem(TWO).isEnabled = false*/
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
                setupWithNavController(navHostFragment.navController)
            }
        }
    }
}