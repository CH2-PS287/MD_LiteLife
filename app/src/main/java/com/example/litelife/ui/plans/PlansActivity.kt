package com.example.litelife.ui.plans

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.litelife.R
import com.example.litelife.databinding.ActivityPlansBinding
import com.example.litelife.ui.home.HomeActivity
import com.example.litelife.ui.tracker.TrackerActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PlansActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlansBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlansBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        sectionPagerAdapter.appName = resources.getString(R.string.app_name)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.navigation_plans
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity((Intent(this, HomeActivity::class.java)))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_tracker -> {
                    startActivity((Intent(this, TrackerActivity::class.java)))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_plans -> {
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.food,
            R.string.exercise,
        )
    }
}