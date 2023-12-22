package com.example.litelife.ui.tracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.R
import com.example.litelife.databinding.ActivityTrackerBinding
import com.example.litelife.ui.home.HomeActivity
import com.example.litelife.ui.plans.PlansActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class TrackerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrackerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.navigation_tracker
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity((Intent(this, HomeActivity::class.java)))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_tracker -> {
                    true
                }
                R.id.navigation_plans -> {
                    startActivity((Intent(this, PlansActivity::class.java)))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }

        binding.toggleAdd.setOnClickListener {
            startActivity((Intent(this, AddTrackerActivity::class.java)))
        }
    }
}