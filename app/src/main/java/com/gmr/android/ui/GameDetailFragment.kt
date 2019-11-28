package com.gmr.android.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gmr.android.R
import com.gmr.android.data.Results
import com.gmr.android.databinding.FragmentGameDetailBinding
import com.gmr.android.viewmodel.SharedViewModel
import com.gmr.android.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GameDetailFragment : DaggerFragment() {
    private lateinit var viewModel: SharedViewModel
    lateinit var binding: FragmentGameDetailBinding

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

     binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_detail, container, false)

        viewModel.selectedGame.observe(this, Observer<Results> {
            binding.result = it
            var platformtext = ""
            var genresText = ""

            for(element in it.parent_platforms){ platformtext += element.platform.name + "/" }
            for (element in it.genres){ genresText += element.name + "/" }

            binding.gameDetailPlatformsTextView.text = platformtext
            binding.gameDetailGenresTextView.text = genresText
        })
        return binding.root
    }
}
