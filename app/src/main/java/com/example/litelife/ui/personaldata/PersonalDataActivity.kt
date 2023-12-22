package com.example.litelife.ui.personaldata

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.R
import com.example.litelife.ViewModelFactory
import com.example.litelife.databinding.ActivityPersonalDataBinding
import com.example.litelife.ui.home.HomeActivity
import com.example.litelife.ui.main.MainViewModel


class PersonalDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalDataBinding
    private lateinit var token: String
    private val viewModel by viewModels<PersonalDataViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Pria", "Wanita")
        val adapter = ArrayAdapter(this, R.layout.list_dropdown_gender, items)
        binding.genderDropdown.setAdapter(adapter)
        binding.genderDropdown.setDropDownBackgroundResource(R.color.white)

        mainViewModel.getSession().observe(this) { userModel ->
            token = userModel.token
            Log.d("PersonalDataActivity", "Token: $token")
        }

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
            val age = binding.ageEditText.text.toString().toInt()
            val weight = binding.weightEditText.text.toString().toDouble()
            val height = binding.heightEditText.text.toString().toDouble()

            if (TextUtils.isEmpty(gender) || age == null || weight == null || height == null) {
                Toast.makeText(
                    this@PersonalDataActivity,
                    "Seluruh bagian wajib diisi!",
                    Toast.LENGTH_SHORT
                ).show()
                showLoading(false)
            } else {
                viewModel.saveUserData(
                    token,
                    gender,
                    height,
                    weight,
                    age
                )
            }
        }

        viewModel.userDataResponse.observe(this) {
            showLoading(false)
            Toast.makeText(this@PersonalDataActivity, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            viewModel.saveFilled()
            startActivity(Intent(this, HomeActivity::class.java))
        }

        viewModel.error.observe(this) { errorMessage ->
            showLoading(false)
            Toast.makeText(this@PersonalDataActivity, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}