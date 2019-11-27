package com.gmr.android.data

import com.google.gson.annotations.SerializedName
/*	This Class Created and Kept For Possible Future Usages
	Currently not in Usage
 */
data class Stores (

	@SerializedName("id") val id : Int,
	@SerializedName("store") val store : Store,
	@SerializedName("url_en") val url_en : String,
	@SerializedName("url_ru") val url_ru : String
)