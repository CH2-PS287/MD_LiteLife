package com.example.litelife.ui.plans

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.litelife.data.response.ErrorResponse
import com.example.litelife.data.response.ExerciseResponse
import com.example.litelife.data.response.ExerciseResponseItem
import com.example.litelife.data.response.FoodResponse
import com.example.litelife.data.response.FoodResponseItem
import com.example.litelife.data.retrofit.ApiConfig
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   PlansViewModel : ViewModel()  {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _listExercise = MutableLiveData<List<ExerciseResponseItem>>()
    val listExercise: LiveData<List<ExerciseResponseItem>> = _listExercise

    private val _listFood = MutableLiveData<List<FoodResponseItem>>()
    val listFood: LiveData<List<FoodResponseItem>> = _listFood

    private val _error = MutableLiveData<ErrorResponse>()
    val error: MutableLiveData<ErrorResponse> = _error
    fun getExercise(token: String) {
        val apiService = ApiConfig.getApiService(token)
        val client = apiService.getExercise()
        client.enqueue(object : Callback<ExerciseResponse> {
            override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val updatedList = mutableListOf<ExerciseResponseItem>()
                        it.exerciseResponse.let { exercise ->
                            updatedList.addAll(exercise)
                        }
                        _listExercise.postValue(updatedList)
                    }
                } else {
                    response.errorBody()?.let {
                        val jsonInString = response.errorBody()?.string()
                        Log.d("StoryViewModel", "Error response: $jsonInString")
                        val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                        val errorMessage = errorBody?.message ?: "Unknown error occurred"
                        error.postValue(ErrorResponse(message = errorMessage))
                    }
                }
            }

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                Log.d("StoryViewModel", "OnFailure : ${t.message}")
                error.postValue(ErrorResponse(message = t.message))
            }
        })
    }

    fun getFood(token: String) {
        val apiService = ApiConfig.getApiService(token)
        val client = apiService.getFood()
        client.enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val updatedList = mutableListOf<FoodResponseItem>()
                        it.foodResponse.let { food ->
                            updatedList.addAll(food)
                        }
                        _listFood.postValue(updatedList)
                        Log.d("PlansViewModel", "Food list size: ${updatedList.size}")
                    }
                } else {
                    response.errorBody()?.let {
                        val jsonInString = response.errorBody()?.string()
                        Log.d("StoryViewModel", "Error response: $jsonInString")
                        val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                        val errorMessage = errorBody?.message ?: "Unknown error occurred"
                        error.postValue(ErrorResponse(message = errorMessage))
                    }
                }
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.d("StoryViewModel", "OnFailure : ${t.message}")
                error.postValue(ErrorResponse(message = t.message))
            }
        })
    }
}