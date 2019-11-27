package com.gmr.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmr.android.RAWGRepository
import com.gmr.android.data.Games
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SharedViewModel @Inject
constructor(private val repository: RAWGRepository): ViewModel(){

   val gamesList: MutableLiveData<Games> = MutableLiveData()

    init{
        repository.getGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Timber.i("it.data" + it.data)
                gamesList.value = it.data

                Timber.i("asdasd" + it.message)
                Timber.i("asdasd" + it.status)
            }
    }
}