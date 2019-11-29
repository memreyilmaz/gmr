package com.android.pixabay.model.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gmr.android.data.FIRST_PAGE
import com.gmr.android.data.NetworkState
import com.gmr.android.data.RAWGApiInterface
import com.gmr.android.data.Results
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class GameDataSource(private val compositeDisposable: CompositeDisposable,
                     private val apiService: RAWGApiInterface)
                : PageKeyedDataSource<Int, Results>() {
    private var page = FIRST_PAGE
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Results>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getGames(page)
                .subscribe(
                    {
                        callback.onResult(it.results, null, page+1)
                        networkState.postValue(NetworkState.DONE)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Timber.e("""GameDataSource${it.message}""")
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getGames(params.key+1)
                .subscribe(
                    {
                        if(it.next != "null") {
                            callback.onResult(it.results, params.key+1)
                            networkState.postValue(NetworkState.DONE)
                        }
                        else{
                            networkState.postValue(NetworkState.ENDOFLIST)
                        }
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Timber.e("""GameDataSource${it.message}""")                    }
                )
        )
    }
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {}
}