package com.gmr.android.ui

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
import kotlinx.android.synthetic.main.platforms_item.view.*
import javax.inject.Inject

class GameDetailFragment : DaggerFragment() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var binding: FragmentGameDetailBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = getViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

     binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_detail, container, false)

        viewModel.selectedGame.observe(this, Observer<Results> {
            binding.result = it
            var platformtext = ""
            var genresText = " "

            for(element in it.parent_platforms){ platformtext += element.platform.slug + "/" }
            for (element in it.genres){ genresText += element.name + " " }

            setPlatforms(platformtext)
            binding.gameDetailGenresTextView.text = genresText
        })

        return binding.root
    }

    private fun getViewModel() : SharedViewModel {
        return activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    private fun setPlatforms(platformtext:String){
        if (platformtext.contains("pc"))
            binding.detailPlatformsLayout.platform_windows_imageView.visibility =
                View.VISIBLE else View.GONE
        if (platformtext.contains("playstation"))
            binding.detailPlatformsLayout.platform_playStation_imageView.visibility =
                View.VISIBLE else View.GONE
        if (platformtext.contains("xbox"))
            binding.detailPlatformsLayout.platform_xBox_imageView.visibility =
                View.VISIBLE else View.GONE
        if (platformtext.contains("ios"))
            binding.detailPlatformsLayout.platform_ios_imageView.visibility =
                View.VISIBLE else View.GONE
        if (platformtext.contains("android"))
            binding.detailPlatformsLayout.platform_android_imageView.visibility =
                View.VISIBLE else View.GONE
        if (platformtext.contains("mac"))
            binding.detailPlatformsLayout.platform_mac_imageView.visibility =
                View.VISIBLE else View.GONE
        if (platformtext.contains("linux"))
            binding.detailPlatformsLayout.platform_linux_imageView.visibility =
                View.VISIBLE else View.GONE
        if (platformtext.contains("nintendo"))
            binding.detailPlatformsLayout.platform_nintendo_imageView.visibility =
                View.VISIBLE else View.GONE
        if (platformtext.contains("web"))
            binding.detailPlatformsLayout.platform_web_imageView.visibility =
                View.VISIBLE else View.GONE
    }
}
