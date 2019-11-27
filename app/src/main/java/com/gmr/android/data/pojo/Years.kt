package com.gmr.android.data

import com.google.gson.annotations.SerializedName
/*	This Class Created and Kept For Possible Future Usages
	Currently not in Usage
 */
data class Years (

	@SerializedName("from") val from : Int,
	@SerializedName("to") val to : Int,
	@SerializedName("filter") val filter : String,
	@SerializedName("decade") val decade : Int,
	@SerializedName("years") val years : List<Years>,
	@SerializedName("nofollow") val nofollow : Boolean,
	@SerializedName("count") val count : Int
)