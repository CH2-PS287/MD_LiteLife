package com.example.litelife.data.response

import com.google.gson.annotations.SerializedName

data class SignupResponse(

    @field:SerializedName("result")
    val result: SignupResult? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class SignupResult(

    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("token")
    val token: String? = null
)
