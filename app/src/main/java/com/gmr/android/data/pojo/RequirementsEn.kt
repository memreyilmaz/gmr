package com.gmr.android.data

import com.google.gson.annotations.SerializedName
/*	This Class Created and Kept For Possible Future Usages
	Currently not in Usage
 */
data class RequirementsEn (

	@SerializedName("minimum") val minimum : String,
	@SerializedName("recommended") val recommended : String
)