package com.gmr.android.data

import com.google.gson.annotations.SerializedName

data class ShortScreenshots (

	@SerializedName("id") val id : Int,
	@SerializedName("image") val image : String
)