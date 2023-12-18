package com.example.litelife.ui.plans

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.litelife.ui.plans.tabLayoutPlans.ExerciseFragment
import com.example.litelife.ui.plans.tabLayoutPlans.FoodFragment

class SectionPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {
    var appName: String = ""

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FoodFragment()
            1 -> ExerciseFragment()
            else -> throw IllegalArgumentException("Invalid tab position: $position")
        }
    }

}