package com.gmr.android.data

import com.google.gson.annotations.SerializedName

//Comment Outed Lines Created and Kept For Possible Future Usages

data class Games (

	//@SerializedName("count") val count : Int,
	@SerializedName("next") val next : String,
	//@SerializedName("previous") val previous : String,
	@SerializedName("results") val results : List<Results>,
	//@SerializedName("seo_title") val seo_title : String,
	//@SerializedName("seo_description") val seo_description : String,
	//@SerializedName("seo_keywords") val seo_seo_keywordswords : String,
	//@SerializedName("seo_h1") val seo_h1 : String,
	//@SerializedName("noindex") val noindex : Boolean,
	//@SerializedName("nofollow") val nofollow : Boolean,
	@SerializedName("description") val description : String//,
	//@SerializedName("filters") val filters : Filters,
	//@SerializedName("nofollow_collections") val nofollow_collections : List<String>
)