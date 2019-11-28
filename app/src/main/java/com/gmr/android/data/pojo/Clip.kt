package com.gmr.android.data

import com.google.gson.annotations.SerializedName
/*	This Class Created and Kept For Possible Future Usages
 	Currently not in Usage
 */
data class Clip (

	@SerializedName("clip") val clip : String,
	@SerializedName("clips") val clips : Clips,
	@SerializedName("video") val video : String,
	@SerializedName("preview") val preview : String
)