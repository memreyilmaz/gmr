package com.gmr.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gmr.android.data.NetworkState
import com.gmr.android.data.RAWGRepository
import com.gmr.android.data.Results
import javax.inject.Inject

class SharedViewModel @Inject constructor(private val repository: RAWGRepository): ViewModel(){

    val selectedGame = MutableLiveData<Results>()

    val gamesList : LiveData<PagedList<Results>> by lazy {
        repository.getGames()
    }

    val networkState : LiveData<NetworkState> by lazy {
        repository.getNetworkState()
    }

    fun setSelectedGame(result: Results) {
        selectedGame.value = result
    }

    fun listIsEmpty(): Boolean {
        return gamesList.value?.isEmpty() ?: true
    }
}