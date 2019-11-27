package com.gmr.android.data

import com.google.gson.annotations.SerializedName
/*	This Class Created and Kept For Possible Future Usages
	Currently not in Usage
 */
data class Clips (

	@SerializedName("320") val px320 : String,
	@SerializedName("640") val px640 : String,
	@SerializedName("full") val full : String
)