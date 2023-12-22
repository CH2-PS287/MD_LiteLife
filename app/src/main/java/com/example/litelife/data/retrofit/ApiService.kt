package com.example.litelife.data.retrofit

import com.example.litelife.data.response.DataUserResponse
import com.example.litelife.data.response.ExerciseResponse
import com.example.litelife.data.response.FoodResponse
import com.example.litelife.data.response.LoginResponse
import com.example.litelife.data.response.SignupResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("auth/sign-up")
    fun register(
        @Field("full_name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SignupResponse>

    @FormUrlEncoded
    @POST("auth/sign-in")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @PATCH("users/profile")
    fun bmiUser(
        @Field("gender") gender: String,
        @Field("height") height: Double,
        @Field("weight") weight: Double,
        @Field("age") age: Int,
    ): Call<DataUserResponse>

    @GET("exercises")
    fun getExercise(): Call<ExerciseResponse>

    @GET("foods")
    fun getFood(): Call<FoodResponse>
}