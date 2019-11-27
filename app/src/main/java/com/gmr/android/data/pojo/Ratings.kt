package com.gmr.android.data

import com.google.gson.annotations.SerializedName
/*	This Class Created and Kept For Possible Future Usages
	Currently not in Usage
 */
data class Ratings (

	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("count") val count : Int,
	@SerializedName("percent") val percent : Double
)