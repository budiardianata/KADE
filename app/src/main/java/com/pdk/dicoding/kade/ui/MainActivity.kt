package com.pdk.dicoding.kade.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navController
        )
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.eventFragment, R.id.leagueFragment
            )
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.apply {
                when (destination.id) {
                    R.id.leagueDetailFragment -> {
                        appbarLayout.setExpanded(false, false)
                        bottomAppBar.visibility = View.GONE
                    }
                    R.id.eventDetailsFragment -> {
                        appbarLayout.setExpanded(false, false)
                        bottomAppBar.visibility = View.GONE
                    }
                    R.id.leagueDetailDialogFragment -> {
                        appbarLayout.setExpanded(false, false)
                        bottomAppBar.visibility = View.GONE
                    }
                    R.id.leagueFragment -> {
                        appbarLayout.setExpanded(true, true)
                        bottomAppBar.visibility = View.VISIBLE
                    }
                    R.id.eventFragment -> {
                        appbarLayout.setExpanded(true, true)
                        bottomAppBar.visibility = View.VISIBLE
                    }
                    else -> {
                        appbarLayout.setExpanded(true, true)
                        bottomAppBar.visibility = View.GONE
                    }
                }
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()


}