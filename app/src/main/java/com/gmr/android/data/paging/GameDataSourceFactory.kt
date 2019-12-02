package com.android.pixabay.model.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gmr.android.data.repository.RAWGApiInterface
import com.gmr.android.data.Results
import io.reactivex.disposables.CompositeDisposable

class GameDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                            private val apiService: RAWGApiInterface)
                    : DataSource.Factory<Int, Results>() {

    val sourceLiveData = MutableLiveData<GameDataSource>()

    override fun create(): DataSource<Int, Results> {
        val source = GameDataSource(compositeDisposable, apiService)
        sourceLiveData.postValue(source)
        return source
    }
}