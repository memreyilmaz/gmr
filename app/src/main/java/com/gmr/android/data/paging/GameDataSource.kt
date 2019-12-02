package com.android.pixabay.model.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gmr.android.data.FIRST_PAGE
import com.gmr.android.data.NetworkState
import com.gmr.android.data.repository.RAWGApiInterface
import com.gmr.android.data.Results
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GameDataSource(private val compositeDisposable: CompositeDisposable,
                     private val apiService: RAWGApiInterface)
    : PageKeyedDataSource<Int, Results>() {

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Results>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getGamesList(FIRST_PAGE)
                .subscribe(
                    {
                        callback.onResult(it.results, null, FIRST_PAGE)
                        networkState.postValue(NetworkState.DONE)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Timber.e("""GameDataSource${it.message}""")
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getGamesList(params.key+1)
                .subscribe(
                    {
                        callback.onResult(it.results, params.key+1)
                        if(it.next != "null") {
                            networkState.postValue(NetworkState.DONE)
                        } else{
                            networkState.postValue(NetworkState.ENDOFLIST)
                        }
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Timber.e("""GameDataSource${it.message}""")
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {}

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}