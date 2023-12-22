package com.example.litelife.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.litelife.data.model.PersonalDataModel
import com.example.litelife.data.model.UserModel
import com.example.litelife.data.repo.UserRepository
import com.example.litelife.data.response.ErrorResponse
import com.example.litelife.data.response.LoginResponse
import com.example.litelife.data.response.SignupResponse
import com.example.litelife.data.retrofit.ApiConfig
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    private val _signupUser = MutableLiveData<SignupResponse>()
    val signupUser: MutableLiveData<SignupResponse> = _signupUser

    private val _loginUser = MutableLiveData<LoginResponse>()
    val loginUser: MutableLiveData<LoginResponse> = _loginUser

    private val _error = MutableLiveData<ErrorResponse>()
    val error: MutableLiveData<ErrorResponse> = _error

    private val isLogin: UserModel = runBlocking { repository.getSession().first() }

    companion object {
        private const val TAG = "AuthViewModel"
    }

    fun login(email: String, password: String) {
        val client = ApiConfig.getApiService(isLogin.token).login(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginUser.value = response.body()
                    _loginUser.postValue(response.body())

                } else {
                    try {
                        val jsonInString = response.errorBody()?.string()
                        val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                        val errorMessage = errorBody?.message ?: "Unknown error occurred"
                        error.postValue(ErrorResponse(message = errorMessage))
                    } catch (e: Exception) {
                        error.postValue(ErrorResponse(message = e.message))
                    }

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d(TAG, "OnFailure : ${t.message}")
                error.postValue(ErrorResponse(message = t.message))
            }
        })
    }

    fun register(name: String, email: String, password: String) {
        val client = ApiConfig.getApiService(isLogin.token).register(name, email, password)
        client.enqueue(object : Callback<SignupResponse> {
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                if (response.isSuccessful) {
                    signupUser.postValue(response.body())
                } else {
                    try {
                        val jsonInString = response.errorBody()?.string()
                        val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)

                        if (errorBody?.message is String) {
                            val errorMessage = errorBody.message
                            error.postValue(ErrorResponse(message = errorMessage))
                        } else {
                            val errorMessage = "Unknown error occurred"
                            error.postValue(ErrorResponse(message = errorMessage))
                        }
                    } catch (e: Exception) {
                        error.postValue(ErrorResponse(message = e.message))
                    }
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Log.d(TAG, "OnFailure : ${t.message}")
                error.postValue(ErrorResponse(message = t.message))
            }
        })
    }


    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun getFilled(): LiveData<PersonalDataModel> {
        return repository.getFilled().asLiveData()
    }
}
