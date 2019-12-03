package com.gmr.android.ui.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.gmr.android.R
import com.squareup.picasso.Picasso

object GameDetailImageBindingAdapter {

        @JvmStatic
        @BindingAdapter(value = ["background_image", "error"], requireAll = false)
        fun loadImage(view: ImageView, url: String?, error:Drawable){
                Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_rawg_logo)
                    .error(error)
                    .fit()
                    .centerCrop()
                    .into(view)
        }
}