package com.gmr.android.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gmr.android.R
import com.gmr.android.data.NetworkState
import com.gmr.android.viewmodel.ViewModelFactory
import com.gmr.android.viewmodel.SharedViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class GameListFragment : DaggerFragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var gamesListAdapter: GamesListAdapter
    private lateinit var gamesRecyclerView : RecyclerView
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
        gamesRecyclerView = view.findViewById(R.id.game) as RecyclerView

        initAdapter()
     //   setViews()

        return view

    }

   /* private fun setViews() {
        viewModel.networkState.observe(this, Observer {
            progressBar.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            no_internet_textView.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()){gamesListAdapter.setNetworkState}
        })    }*/

    private fun initAdapter() {
        gamesListAdapter = GamesListAdapter()
        gamesRecyclerView.adapter = gamesListAdapter

        viewModel.gamesList.observe(this, Observer {
            gamesListAdapter.submitList(it)
            if(isTablet == true){
                gamesListAdapter.getGameAtPosition(0)?.let { it1 -> viewModel.setSelectedGame(it1) }
            }
        })

        gamesListAdapter.setOnItemClickListener(object:
            GamesListAdapter.ClickListener {
            override fun onItemClick(v: View, pos: Int) {
                viewModel.setSelectedGame(gamesListAdapter.getGameAtPosition(pos)!!)
                if(isTablet == false){
                    view?.findNavController()?.navigate(R.id.action_gameListFragment_to_gameDetailFragment)
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
