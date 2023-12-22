package com.example.litelife.data.repo

import android.util.Log
import com.example.litelife.data.model.PersonalDataModel
import com.example.litelife.data.model.UserModel
import com.example.litelife.data.pref.UserPreference
import com.example.litelife.data.response.DataUserResponse
import com.example.litelife.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import retrofit2.Callback

class UserRepository private constructor(
    private val userPreference: UserPreference,
) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    fun getFilled(): Flow<PersonalDataModel> {
        return userPreference.getFilled()
    }

    suspend fun saveFilled() {
        userPreference.saveFilled()
    }
    suspend fun logout() {
        Log.d("UserRepository", "Logout called")
        userPreference.logout()
    }

    fun saveUserData(
        token: String,
        gender: String,
        height: Double,
        weight: Double,
        age: Int,
        callback: Callback<DataUserResponse>
    ) {
        val client= ApiConfig.getApiService(token).bmiUser(gender, height, weight, age)
        client.enqueue(callback)
    }
    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }

}