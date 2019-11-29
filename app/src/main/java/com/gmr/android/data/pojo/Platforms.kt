package com.gmr.android.data

import com.google.gson.annotations.SerializedName
/*	This Class Created and Kept For Possible Future Usages
	Currently not in Usage
 */
data class Platforms (

	@SerializedName("platform") val platform : Platform,
	@SerializedName("released_at") val released_at : String,
	@SerializedName("requirements_en") val requirements_en : RequirementsEn,
	@SerializedName("requirements_ru") val requirements_ru : String
)