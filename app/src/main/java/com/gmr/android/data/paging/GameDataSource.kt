package com.android.pixabay.model.paging

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gmr.android.data.RAWGApiInterface
import com.gmr.android.data.Resource
import com.gmr.android.data.Results
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class GameDataSource(private val subscriptions: CompositeDisposable,
                     private val apiService: RAWGApiInterface)
                : PageKeyedDataSource<Int, Results>() {

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Results>) {


        subscriptions.add(
            apiService.getGames(1)
                .subscribe(
                    { response ->
                        //if (response.totalHits == 0){
                          //  network.postValue(NetworkState.error("no result"))
                        //}else{

                            callback.onResult(response.results, null, 2)
                       // }
                    },
                    {
                     //   setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {
        subscriptions.add(
            apiService.getGames(params.key+1)
                .subscribe(
                    { response ->
                        callback.onResult(response.results, params.key + 1)
                    },
                    {
                       // setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Results>) {}

   /* private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

    fun retry() {
        if (retryCompletable != null) {
            subscriptions.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }*/
}