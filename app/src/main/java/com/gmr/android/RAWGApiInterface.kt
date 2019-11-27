package com.gmr.android

import com.gmr.android.data.Games
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RAWGApiInterface {

    @GET(".")
    fun getGames (@Query("page_size") page: Int): Single<Games>

}