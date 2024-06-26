package com.example.easyfoodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.easyfoodapp.R
import com.example.easyfoodapp.viewModels.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayNavbar()

    }

    private fun displayNavbar() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = Navigation.findNavController(this, R.id.host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}