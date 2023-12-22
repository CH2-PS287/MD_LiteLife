package com.example.litelife.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.litelife.data.model.UserModel
import com.example.litelife.data.repo.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            try {
                repository.logout()
                Log.d("PersonalDataViewModel", "Logout successful")
            } catch (e: Exception) {
                Log.e("PersonalDataViewModel", "Error during logout: ${e.message}", e)
            }
        }
    }
}