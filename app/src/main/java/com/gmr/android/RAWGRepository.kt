package com.gmr.android

import com.gmr.android.data.Games
import com.gmr.android.data.Resource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RAWGRepository @Inject
constructor (private val networkService: RAWGApiInterface){

    fun getGames(): Observable<Resource<Games>> {
        return Observable.create { emitter ->

            emitter.onNext(Resource.loading())

            networkService
                .getGames(2)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        emitter.onNext(Resource.success(it))
                        emitter.onComplete()
                    },
                    {
                        emitter.onNext(Resource.error(it.message ?: "Error"))
                        Timber.e(it)
                        emitter.onComplete()
                    }
                )
        }
    }
}