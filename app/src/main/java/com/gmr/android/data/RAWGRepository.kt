package com.gmr.android.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.pixabay.model.paging.GameDataSourceFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RAWGRepository @Inject constructor (private val networkService: RAWGApiInterface){
    lateinit var gamesDataSourceFactory: GameDataSourceFactory
    lateinit var gamesList: LiveData<PagedList<Results>>
    private val subscriptions = CompositeDisposable()

    fun getGames(): LiveData<PagedList<Results>> {
     gamesDataSourceFactory = GameDataSourceFactory(subscriptions, networkService)

     val config = PagedList.Config.Builder()
         .setPageSize(PAGE_SIZE)
         .setInitialLoadSizeHint(PAGE_SIZE)
         .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
         .build()

        gamesList = LivePagedListBuilder<Int, Results>(gamesDataSourceFactory, config).build()
        return gamesList

    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val ENABLE_PLACEHOLDERS = false
    }
}