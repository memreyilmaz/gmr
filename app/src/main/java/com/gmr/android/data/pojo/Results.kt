package com.gmr.android.data

import com.google.gson.annotations.SerializedName

// Comment Outed Lines Created and Kept For Possible Future Usages

data class Results (

	@SerializedName("id") val id : Int,
	@SerializedName("slug") val slug : String,
	@SerializedName("name") val name : String,
	@SerializedName("released") val released : String,
	//@SerializedName("tba") val tba : Boolean,
	@SerializedName("background_image") val background_image : String,
	@SerializedName("rating") val rating : Double,
	@SerializedName("rating_top") val rating_top : Int,
	//@SerializedName("ratings") val ratings : List<Ratings>,
	//@SerializedName("ratings_count") val ratings_count : Int,
	//@SerializedName("reviews_text_count") val reviews_text_count : Int,
	//@SerializedName("added") val added : Int,
	//@SerializedName("added_by_status") val added_by_status : AddedByStatus,
	//@SerializedName("metacritic") val metacritic : Int,
	//@SerializedName("playtime") val playtime : Int,
	//@SerializedName("suggestions_count") val suggestions_count : Int,
	//@SerializedName("user_game") val user_game : String,
	//@SerializedName("reviews_count") val reviews_count : Int,
	@SerializedName("saturated_color") val saturated_color : String,
	@SerializedName("dominant_color") val dominant_color : String,
	//@SerializedName("platforms") val platforms : List<Platforms>,
	@SerializedName("parent_platforms") val parent_platforms : List<ParentPlatforms>,
	@SerializedName("genres") val genres : List<Genres>,
	//@SerializedName("stores") val stores : List<Stores>,
	//@SerializedName("clip") val clip : Clip,
	@SerializedName("short_screenshots") val short_screenshots : List<ShortScreenshots>
)