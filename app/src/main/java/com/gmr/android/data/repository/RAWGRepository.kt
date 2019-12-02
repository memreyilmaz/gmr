package com.gmr.android.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.pixabay.model.paging.GameDataSource
import com.android.pixabay.model.paging.GameDataSourceFactory
import com.gmr.android.data.ENABLE_PLACEHOLDERS
import com.gmr.android.data.NetworkState
import com.gmr.android.data.PAGE_SIZE
import com.gmr.android.data.Results
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RAWGRepository @Inject constructor (private val apiService: RAWGApiInterface){
    private lateinit var gamesDataSourceFactory: GameDataSourceFactory
    lateinit var gamesList: LiveData<PagedList<Results>>
    private val compositeDisposable = CompositeDisposable()

    fun getGames(): LiveData<PagedList<Results>> {
     gamesDataSourceFactory = GameDataSourceFactory(compositeDisposable, apiService)

     val config = PagedList.Config.Builder()
         .setPageSize(PAGE_SIZE)
         .setInitialLoadSizeHint(PAGE_SIZE)
         .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
         .build()

        gamesList = LivePagedListBuilder<Int, Results>(gamesDataSourceFactory, config).build()
        return gamesList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<GameDataSource, NetworkState>(
            gamesDataSourceFactory.sourceLiveData, GameDataSource::networkState)
    }
    fun retry() {
        gamesDataSourceFactory.sourceLiveData.value?.retry()
    }
}