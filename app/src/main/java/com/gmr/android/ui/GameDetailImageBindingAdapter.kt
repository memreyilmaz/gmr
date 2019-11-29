package com.gmr.android.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.gmr.android.R
import com.squareup.picasso.Picasso

object GameDetailImageBindingAdapter {

        @JvmStatic
        @BindingAdapter("background_image")
        fun setImageUrl(view: ImageView, url: String){
            Picasso.get()
                .load(url)
                    //TODO add drawable image
                .placeholder(
                    R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(view)
        }
}