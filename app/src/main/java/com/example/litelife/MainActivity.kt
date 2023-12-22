package com.example.litelife

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.databinding.ActivityMainBinding
import com.example.litelife.ui.auth.AuthViewModel
import com.example.litelife.ui.home.HomeActivity
import com.example.litelife.ui.main.MainViewModel
import com.example.litelife.ui.personaldata.PersonalDataActivity
import com.example.litelife.ui.splashscreen.SplashScreenActivity


class MainActivity : AppCompatActivity() {
    private lateinit var token: String
    private lateinit var binding: ActivityMainBinding

    private val authViewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            if (user.isLogin) {
                token = user.token
                checkFilled()
            } else {
                startActivity(Intent(this, SplashScreenActivity::class.java))
                finish()
            }
        }
    }

    private fun checkFilled() {
        authViewModel.getFilled().observe(this) { user ->
            if (user.dataFilled) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, PersonalDataActivity::class.java))
                finish()
            }
        }
    }
}