package com.android.pixabay.model.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gmr.android.data.RAWGApiInterface
import com.gmr.android.data.Resource
import com.gmr.android.data.Results
import io.reactivex.disposables.CompositeDisposable

class GameDataSourceFactory(private val subscriptions: CompositeDisposable, private val apiService: RAWGApiInterface)
                    : DataSource.Factory<Int, Results>() {

    val sourceLiveData = MutableLiveData<GameDataSource>()

    override fun create(): DataSource<Int, Results> {
        val source = GameDataSource(subscriptions, apiService)
        sourceLiveData.postValue(source)
        return source
    }
}