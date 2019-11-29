package com.gmr.android.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmr.android.R
import com.gmr.android.data.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.game_item.view.*

class GamesListAdapter : PagedListAdapter<Results, GamesListAdapter.GamesItemViewHolder>(GamesDiffCallback) {
    private lateinit var mContext : Context
    var mGames : List<Results>? = null
    lateinit var mClickListener: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesItemViewHolder {
        mContext = parent.context
        val view : View = LayoutInflater.from(mContext).inflate(R.layout.game_item, parent, false)
        return GamesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GamesItemViewHolder, position: Int) {
        val game: Results? = getItem(position)
        var genresText = ""
        var platformtext = ""

        if (game != null) {
            for (element in game.genres){ genresText += element.name + "/"}
            for(element in game.parent_platforms){ platformtext += element.platform.name + "/"}
        }
        holder.itemView.game_name_textView.text = game?.name
        holder.itemView.game_rating_textView.text = game?.rating.toString()
        holder.itemView.game_rating_top_textView.text = game?.rating_top.toString()
        holder.itemView.game_platforms_textView.text = platformtext
        holder.itemView.game_genres_textView.text = genresText
        Picasso.get().load(game?.background_image).into(holder.itemView.game_image)

    }

    fun getGameAtPosition(position: Int): Results? {
        return currentList?.get(position)
    }

    fun setOnItemClickListener(clickListener: ClickListener){
        mClickListener = clickListener
    }

    interface ClickListener {

        fun onItemClick(v: View, position: Int)
    }

    inner class GamesItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        override fun onClick(v: View) {
            mClickListener.onItemClick(v, adapterPosition)
        }
        init {
            view.tag = this
            view.setOnClickListener(this)
        }
    }

    companion object {
        val GamesDiffCallback = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }
        }
    }
}