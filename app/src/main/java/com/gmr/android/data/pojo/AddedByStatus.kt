package com.gmr.android.data

import com.google.gson.annotations.SerializedName
/*	This Class Created and Kept For Possible Future Usages
	Currently not in Usage
 */
data class AddedByStatus (

	@SerializedName("yet") val yet : Int,
	@SerializedName("owned") val owned : Int,
	@SerializedName("beaten") val beaten : Int,
	@SerializedName("toplay") val toplay : Int,
	@SerializedName("dropped") val dropped : Int,
	@SerializedName("playing") val playing : Int
)