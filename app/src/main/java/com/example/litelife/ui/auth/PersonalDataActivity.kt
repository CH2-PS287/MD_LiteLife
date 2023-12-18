package com.example.litelife.ui.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.R
import com.example.litelife.databinding.ActivityPersonalDataBinding
import com.example.litelife.ui.main.MainActivity

class PersonalDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Laki-laki", "Perempuan")
        val adapter = ArrayAdapter(this, R.layout.list_dropdown_gender, items)
        binding.genderDropdown.setAdapter(adapter)
        binding.genderDropdown.setDropDownBackgroundResource(R.color.white)

        showLoading(false)
        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.nextButton.setOnClickListener {
            showLoading(true)
            val gender = binding.genderDropdown.text.toString()
            val age = binding.ageEditText.text.toString()
            val weight = binding.weightEditText.text.toString().toDoubleOrNull()
            val height = binding.heightEditText.text.toString().toDoubleOrNull()

            if (TextUtils.isEmpty(gender) || TextUtils.isEmpty(age) || weight == null || height == null) {
                Toast.makeText(
                    this@PersonalDataActivity,
                    "Seluruh bagian wajib diisi!",
                    Toast.LENGTH_SHORT
                ).show()
                showLoading(false)
            } else {
                val gender = binding.genderDropdown.text.toString()
                val age = binding.ageEditText.text.toString()
                val weight = binding.weightEditText.text.toString().toDoubleOrNull()
                val height = binding.heightEditText.text.toString().toDoubleOrNull()

                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}