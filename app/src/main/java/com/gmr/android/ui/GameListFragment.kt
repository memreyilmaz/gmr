package com.gmr.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.gmr.android.R
import com.gmr.android.data.NetworkState
import com.gmr.android.ui.adapter.GamesListAdapter
import com.gmr.android.viewmodel.SharedViewModel
import com.gmr.android.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_game_list.*
import javax.inject.Inject


class GameListFragment : DaggerFragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var gamesListAdapter : GamesListAdapter
    private lateinit var gamesRecyclerView : RecyclerView
    private lateinit var retryButton : Button
    var isTablet:Boolean? = null

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        isTablet = context?.resources?.getBoolean(R.bool.isTablet)

        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        gamesRecyclerView = view.findViewById(R.id.game_item_recyclerView) as RecyclerView
        gamesRecyclerView.addItemDecoration(DividerItemDecoration(
                gamesRecyclerView.context, DividerItemDecoration.VERTICAL))
        retryButton = view.findViewById(R.id.retry_button) as Button

        initAdapter()

        return view
    }

    private fun initAdapter() {
        gamesListAdapter = GamesListAdapter {
            viewModel.retry()
        }

        gamesRecyclerView.adapter = gamesListAdapter

        viewModel.gamesList.observe(this, Observer {
            gamesListAdapter.submitList(it)
        })

        retryButton.setOnClickListener {
            viewModel.retry()
        }

       viewModel.networkState.observe(this, Observer {
           gameList_fragment_progressBar.visibility =
               if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
           error_message_textView.visibility =
               if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE
           retryButton.visibility =
               if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

           if (!viewModel.listIsEmpty()) gamesListAdapter.setState(it ?: NetworkState.DONE)

           if(isTablet == true && it != NetworkState.ERROR){
               gamesListAdapter.getGameAtPosition(0)
                   ?.let { it1 -> viewModel.setSelectedGame(it1) }
           }
       })

        gamesListAdapter.setOnItemClickListener(object:
            GamesListAdapter.ClickListener {
            override fun onItemClick(v: View, position: Int) {
                viewModel.setSelectedGame(gamesListAdapter.getGameAtPosition(position)!!)
                if(isTablet == false){
                    view?.findNavController()
                        ?.navigate(R.id.action_gameListFragment_to_gameDetailFragment)
                }
            }
        })
    }

    private fun getViewModel() : SharedViewModel {
        return activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
}
