package com.gmr.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gmr.android.data.RAWGRepository
import com.gmr.android.data.Results
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SharedViewModel @Inject
constructor(private val repository: RAWGRepository): ViewModel(){

    val selectedGame = MutableLiveData<Results>()

    lateinit var gamesList : LiveData<PagedList<Results>>

    init {
        getGames()
    }
    fun getGames() {
        gamesList = repository.getGames()
    }

    fun setSelectedGame(result: Results) {
        selectedGame.value = result
    }
}