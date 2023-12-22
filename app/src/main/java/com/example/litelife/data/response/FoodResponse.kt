package com.example.litelife.data.response

import com.google.gson.annotations.SerializedName

data class FoodResponse(

	@field:SerializedName("FoodResponse")
	val foodResponse: List<FoodResponseItem>
)

data class FoodResponseItem(

	@field:SerializedName("pantotenat_b5")
	val pantotenatB5: Any? = null,

	@field:SerializedName("lemak_ganda")
	val lemakGanda: Any? = null,

	@field:SerializedName("folat_total_b9")
	val folatTotalB9: Int? = null,

	@field:SerializedName("retinol")
	val retinol: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("protein")
	val protein: Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("mineral")
	val mineral: Int? = null,

	@field:SerializedName("lemak")
	val lemak: Any? = null,

	@field:SerializedName("serat_total")
	val seratTotal: Any? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("fiber")
	val fiber: Int? = null,

	@field:SerializedName("vitamin_c")
	val vitaminC: Any? = null,

	@field:SerializedName("vitamin_a_iu")
	val vitaminAIu: Any? = null,

	@field:SerializedName("vitamin_d")
	val vitaminD: Any? = null,

	@field:SerializedName("vitamin_e")
	val vitaminE: Any? = null,

	@field:SerializedName("gula_total")
	val gulaTotal: Any? = null,

	@field:SerializedName("vitamin_b6")
	val vitaminB6: Any? = null,

	@field:SerializedName("b_kriptosantin")
	val bKriptosantin: Int? = null,

	@field:SerializedName("vitamin_k")
	val vitaminK: Any? = null,

	@field:SerializedName("b_karoten")
	val bKaroten: Int? = null,

	@field:SerializedName("kolina")
	val kolina: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("calorie")
	val calorie: String? = null,

	@field:SerializedName("niasin")
	val niasin: Any? = null,

	@field:SerializedName("likopen")
	val likopen: Int? = null,

	@field:SerializedName("vitamin_d_iu")
	val vitaminDIu: Int? = null,

	@field:SerializedName("kolesterol")
	val kolesterol: Int? = null,

	@field:SerializedName("air")
	val air: Any? = null,

	@field:SerializedName("magnesium_mg")
	val magnesiumMg: Int? = null,

	@field:SerializedName("tembaga_cu")
	val tembagaCu: Any? = null,

	@field:SerializedName("lemak_jenuh")
	val lemakJenuh: Any? = null,

	@field:SerializedName("besi_fe")
	val besiFe: Any? = null,

	@field:SerializedName("lemak_tunggal")
	val lemakTunggal: Any? = null,

	@field:SerializedName("kalsium_ca")
	val kalsiumCa: Int? = null,

	@field:SerializedName("vitamin_a_rae")
	val vitaminARae: Int? = null,

	@field:SerializedName("tiamina_b1")
	val tiaminaB1: Any? = null,

	@field:SerializedName("fosfor_p")
	val fosforP: Int? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("seng_zn")
	val sengZn: Any? = null,

	@field:SerializedName("energi")
	val energi: Int? = null,

	@field:SerializedName("karbohidrat")
	val karbohidrat: Any? = null,

	@field:SerializedName("kalium_k")
	val kaliumK: Any? = null,

	@field:SerializedName("zeaksantin_lutein")
	val zeaksantinLutein: Int? = null,

	@field:SerializedName("abu")
	val abu: Any? = null,

	@field:SerializedName("a_karoten")
	val aKaroten: Int? = null,

	@field:SerializedName("mangan_mn")
	val manganMn: Any? = null,

	@field:SerializedName("natrium_na")
	val natriumNa: Any? = null,

	@field:SerializedName("selenium_se")
	val seleniumSe: Any? = null,

	@field:SerializedName("vitamin_b12")
	val vitaminB12: Any? = null,

	@field:SerializedName("riboflavin_b2")
	val riboflavinB2: Any? = null
)
