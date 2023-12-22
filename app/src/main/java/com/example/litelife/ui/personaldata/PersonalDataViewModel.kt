package com.example.litelife.ui.personaldata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.litelife.data.repo.UserRepository
import com.example.litelife.data.response.DataUserResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalDataViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userDataResponse = MutableLiveData<DataUserResponse>()
    val userDataResponse = _userDataResponse

    private val _error = MutableLiveData<String>()
    val error = _error

    fun saveUserData(
        token: String,
        gender: String,
        height: Double,
        weight: Double,
        age: Int,
    ) {

        userRepository.saveUserData(
            token,
            gender,
            height,
            weight,
            age,
            object : Callback<DataUserResponse> {
                override fun onResponse(
                    call: Call<DataUserResponse>,
                    response: Response<DataUserResponse>
                ) {
                    if (response.isSuccessful) {
                        _userDataResponse.value = response.body()
                    } else {
                        _error.value = "Gagal menyimpan data"
                    }
                }

                override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                    _error.value = t.message ?: "Unknown error occurred."
                }
            }
        )
    }

    fun saveFilled() {
        viewModelScope.launch {
            userRepository.saveFilled()
        }
    }
}