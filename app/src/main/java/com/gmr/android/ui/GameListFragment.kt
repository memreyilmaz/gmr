package com.gmr.android.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.gmr.android.R
import com.gmr.android.viewmodel.ViewModelFactory
import com.gmr.android.viewmodel.SharedViewModel
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class GameListFragment : DaggerFragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var gamesListAdapter: GamesListAdapter
    lateinit var gamesRecyclerView : RecyclerView

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        gamesRecyclerView = view.findViewById(R.id.game) as RecyclerView

        initAdapter()

        return view

    }

    private fun initAdapter() {
        gamesListAdapter = GamesListAdapter()
        gamesRecyclerView.adapter = gamesListAdapter

        viewModel.gamesList.observe(this, Observer {
            gamesListAdapter.setGameData(it)
        })

        gamesListAdapter.setOnItemClickListener(object:
            GamesListAdapter.ClickListener {
            override fun onItemClick(v: View, pos: Int) {
            }
        })
    }
}
