package com.example.litelife.ui.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.R
import com.example.litelife.ViewModelFactory
import com.example.litelife.databinding.ActivitySignupBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {
    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var emailEditText: TextInputEditText
    private lateinit var emailEditTextLayout: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var passwordEditTextLayout: TextInputLayout
    private lateinit var nameEditText: TextInputEditText
    private lateinit var nameEditTextLayout: TextInputLayout
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(false)
        setupAction()
        setupView()
        setupNameEditText()
        setupEmailEditText()
        setupPasswordEditText()
        loginButton()
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

    private fun setupNameEditText() {
        nameEditText = findViewById(R.id.nameEditText)
        nameEditTextLayout = findViewById(R.id.nameEditTextLayout)

        nameEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                nameEditTextLayout.hint = ""
            } else {
                if (nameEditText.text.isNullOrBlank()) {
                    nameEditTextLayout.hint = getString(R.string.auth_namapengguna)
                }
            }
        }
    }

    private fun setupEmailEditText() {
        emailEditText = findViewById(R.id.emailEditText)
        emailEditTextLayout = findViewById(R.id.emailEditTextLayout)

        emailEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailEditTextLayout.hint = ""
            } else {
                if (emailEditText.text.isNullOrBlank()) {
                    emailEditTextLayout.hint = getString(R.string.email)
                }
            }
        }
    }

    private fun setupPasswordEditText() {
        passwordEditText = findViewById(R.id.passwordEditText)
        passwordEditTextLayout = findViewById(R.id.passwordEditTextLayout)

        passwordEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordEditTextLayout.hint = ""
            } else {
                if (passwordEditText.text.isNullOrBlank()) {
                    passwordEditTextLayout.hint = getString(R.string.auth_katasandi)
                }
            }
        }
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            showLoading(true)
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(
                    this@SignupActivity,
                    "Seluruh bagian wajib diisi!",
                    Toast.LENGTH_SHORT
                ).show()
                showLoading(false)
            } else {
                viewModel.register(name, email, password)
            }
        }

        viewModel.error.observe(this) { errorResponse ->
            errorResponse?.let {
                showLoading(false)
                showErrorDialog(errorResponse.message ?: "Unknown error occurred")
            }
        }

        viewModel.signupUser.observe(this) { signupUser ->
            signupUser?.let {
                showLoading(false)
                showSuccessDialog()
            }
        }
    }

    private fun showErrorDialog(errorMessage: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Gagal Mendaftar")
            .setMessage(errorMessage)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showSuccessDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Berhasil Daftar")
            .setMessage("Akun berhasil dibuat")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                startActivity(Intent(this, PersonalDataActivity::class.java))
                finish()
            }
            .create()
            .show()

    }

    private fun loginButton() {
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}