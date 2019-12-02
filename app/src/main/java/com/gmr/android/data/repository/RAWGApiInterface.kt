package com.gmr.android.data.repository

import com.gmr.android.data.Games
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RAWGApiInterface {

    @GET(".")
    fun getGamesList (@Query("page") page: Int): Single<Games>
}