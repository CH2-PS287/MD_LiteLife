package com.example.litelife.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.ViewModelFactory
import com.example.litelife.databinding.ActivityProfileBinding
import com.example.litelife.ui.auth.LoginActivity
import com.example.litelife.ui.main.MainViewModel

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.logoutButton.setOnClickListener {
            Log.d("ProfileActivity", "Logout button clicked")
            mainViewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
