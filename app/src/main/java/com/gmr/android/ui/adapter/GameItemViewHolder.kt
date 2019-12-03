package com.gmr.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmr.android.R
import com.gmr.android.data.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.game_item.view.*
import kotlinx.android.synthetic.main.platforms_item.view.*

class
GameItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(game: Results?) {
        var genresText = ""
        var platformtext = ""

        if (game != null) {
            for(element in game.genres){ genresText += element.name + " "}
            for(element in game.parent_platforms){ platformtext += element.platform.slug + "/"}
        }
        itemView.game_name_textView.text = game?.name
        itemView.game_genres_textView.text = genresText
        Picasso.get()
            .load(game?.background_image)
            .placeholder(R.drawable.ic_rawg_logo)
            .error(R.drawable.ic_rawg_logo)
            .fit()
            .centerInside()
            .into(itemView.game_image)

        //Reorganize ASAP
        if (platformtext.contains("pc"))
            itemView.platform_windows_imageView.visibility = View.VISIBLE else View.GONE
        if (platformtext.contains("playstation"))
            itemView.platform_playStation_imageView.visibility = View.VISIBLE else View.GONE
        if (platformtext.contains("xbox"))
            itemView.platform_xBox_imageView.visibility = View.VISIBLE else View.GONE
        if (platformtext.contains("ios"))
            itemView.platform_ios_imageView.visibility = View.VISIBLE else View.GONE
        if (platformtext.contains("android"))
            itemView.platform_android_imageView.visibility = View.VISIBLE else View.GONE
        if (platformtext.contains("mac"))
            itemView.platform_mac_imageView.visibility = View.VISIBLE else View.GONE
        if (platformtext.contains("linux"))
            itemView.platform_linux_imageView.visibility = View.VISIBLE else View.GONE
        if (platformtext.contains("nintendo"))
            itemView.platform_nintendo_imageView.visibility = View.VISIBLE else View.GONE
        if (platformtext.contains("web"))
            itemView.platform_web_imageView.visibility = View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup): GameItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.game_item, parent, false)
            return GameItemViewHolder(view)
        }
    }
}