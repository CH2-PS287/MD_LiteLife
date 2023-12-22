package com.example.litelife.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.litelife.R
import com.example.litelife.databinding.ActivityHomeBinding
import com.example.litelife.ui.plans.PlansActivity
import com.example.litelife.ui.profile.ProfileActivity
import com.example.litelife.ui.tracker.TrackerActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.navigation_home
        bottomNavigation.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    true
                }
                R.id.navigation_tracker -> {
                    startActivity((Intent(this, TrackerActivity::class.java)))
                    overridePendingTransition(0, 0)
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

        binding.profile.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
