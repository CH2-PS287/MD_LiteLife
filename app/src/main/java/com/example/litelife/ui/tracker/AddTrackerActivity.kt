package com.example.litelife.ui.tracker

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.R
import com.example.litelife.databinding.ActivityAddTrackerBinding

class AddTrackerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTrackerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Laki-laki", "Perempuan")
        val adapter = ArrayAdapter(this, R.layout.list_dropdown_category, items)
        binding.categoryDropdown.setAdapter(adapter)
        binding.categoryDropdown.setDropDownBackgroundResource(R.color.white)
    }
}