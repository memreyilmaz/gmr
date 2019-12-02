package com.gmr.android.data

class NetworkState(val status: Status, val msg: String) {

    companion object {
        val DONE: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState

        init {
            DONE = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.LOADING, "Loading")
            ERROR = NetworkState(Status.ERROR, "Something went wrong")
            ENDOFLIST = NetworkState(Status.ERROR, "You have reached the end")
        }
    }
}