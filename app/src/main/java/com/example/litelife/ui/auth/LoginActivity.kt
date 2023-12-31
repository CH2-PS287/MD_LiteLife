package com.example.litelife.ui.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.litelife.R
import com.example.litelife.ViewModelFactory
import com.example.litelife.data.model.UserModel
import com.example.litelife.databinding.ActivityLoginBinding
import com.example.litelife.ui.home.HomeActivity
import com.example.litelife.ui.personaldata.PersonalDataActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var emailEditText: TextInputEditText
    private lateinit var emailEditTextLayout: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var passwordEditTextLayout: TextInputLayout
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length < 8) {
                    binding.passwordEditText.error = "Kata sandi tidak boleh kurang dari 8 karakter"
                    binding.passwordEditTextLayout.endIconMode = TextInputLayout.END_ICON_NONE
                } else {
                    binding.passwordEditText.error = null
                    binding.passwordEditTextLayout.endIconMode =
                        TextInputLayout.END_ICON_PASSWORD_TOGGLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        showLoading(false)
        setupAction()
        setupView()
        setupEmailEditText()
        setupPasswordEditText()
        signUpButton()
    }


    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            showLoading(true)
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(
                    this@LoginActivity,
                    "Seluruh bagian wajib diisi!",
                    Toast.LENGTH_SHORT
                ).show()
                showLoading(false)
            } else {
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                viewModel.login(email, password)

            }
        }

        viewModel.loginUser.observe(this) { response ->
            response?.result?.let { loginResult ->
                val name = loginResult.userId ?: "DefaultName"
                val token = loginResult.token ?: "DefaultToken"
                viewModel.saveSession(UserModel(name, token))
            } ?: showErrorDialog("Login result is null")
            showLoading(false)
            showDialog()
        }

        viewModel.error.observe(this) { errorResponse ->
            errorResponse?.let {
                showLoading(false)
                showErrorDialog(errorResponse.message ?: "Unknown error occurred")
            }
        }


    }

    private fun showErrorDialog(errorMessage: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Gagal Masuk")
            .setMessage(errorMessage)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Berhasil Masuk")
            .setMessage("Anda telah berhasil masuk")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                checkFilled()
//                startActivity(Intent(this, HomeActivity::class.java))

            }
            .create()
            .show()
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
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun signUpButton() {
        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun checkFilled() {
        viewModel.getFilled().observe(this) { user ->
            if (user.dataFilled) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, PersonalDataActivity::class.java))
                finish()
            }
        }
    }
}