package com.example.litelife.data.response

import com.google.gson.annotations.SerializedName

data class ExerciseResponse(

	@field:SerializedName("ExerciseResponse")
	val exerciseResponse: List<ExerciseResponseItem>
)

data class ExerciseResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("activity")
	val activity: String? = null,

	@field:SerializedName("calorie")
	val calorie: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("label")
	val label: Int? = null
)
