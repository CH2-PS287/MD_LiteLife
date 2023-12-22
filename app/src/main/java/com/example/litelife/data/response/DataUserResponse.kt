package com.example.litelife.data.response

import com.google.gson.annotations.SerializedName

data class DataUserResponse(
	@field:SerializedName("result")
	val result: DataUserResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataUserResult(

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("recommended_calorie")
	val recommendedCalorie: Any? = null,

	@field:SerializedName("birth_date")
	val birthDate: Any? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
