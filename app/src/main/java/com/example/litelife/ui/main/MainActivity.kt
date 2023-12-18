package com.example.litelife.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.ViewModelFactory
import com.example.litelife.databinding.ActivityMainBinding
import com.example.litelife.ui.home.HomeActivity


class MainActivity : AppCompatActivity() {
    private lateinit var token: String
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Intent(this, HomeActivity::class.java).also {
            startActivity(it)
        }
//        viewModel.getSession().observe(this) { user ->
//            if (user.isLogin) {
//                token = user.token
//            } else {
//                startActivity(Intent(this, SplashScreenActivity::class.java))
//                finish()
//            }
//        }


    }
}