package com.gmr.android.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmr.android.R
import com.gmr.android.data.Results
import com.squareup.picasso.Picasso

class GamesListAdapter : PagedListAdapter<Results, GamesListAdapter.GamesListAdapterViewHolder>(GamesDiffCallback) {
    lateinit var mContext : Context
    var mGames : List<Results>? = null
    lateinit var mClickListener: ClickListener

    inner class GamesListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View) {
            mClickListener.onItemClick(v, adapterPosition)
        }

        var gameNameTextView : TextView
        var gameRatingTextview : TextView
        var gameRatingTopTextview : TextView
        var gamePlatformsTextview : TextView
        var gameGenresTextview : TextView
        var gameImageView : ImageView

        init {
            itemView.tag = this

            gameNameTextView = itemView.findViewById(R.id.game_name_textView)
            gameRatingTextview = itemView.findViewById(R.id.game_rating_textView)
            gameRatingTopTextview = itemView.findViewById(R.id.game_rating_top_textView)
            gamePlatformsTextview = itemView.findViewById(R.id.game_platforms_textView)
            gameGenresTextview = itemView.findViewById(R.id.game_genres_textView)
            gameImageView = itemView.findViewById(R.id.game_image)
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesListAdapterViewHolder {
        mContext = parent.context
        val view : View = LayoutInflater.from(mContext).inflate(R.layout.game_item, parent, false)
        return GamesListAdapterViewHolder(view)
    }
    override fun onBindViewHolder(holder: GamesListAdapterViewHolder, position: Int) {
        val game: Results? = mGames?.get(position)
        var genresText = ""
        var platformtext = ""

        if (game != null) {
            for (element in game.genres){ genresText += element.name + "/"}
            for(element in game.parent_platforms){ platformtext += element.platform.name + "/"}
        }

        holder.gameNameTextView.text = game?.name
        holder.gameRatingTextview.text = game?.rating.toString()
        holder.gameRatingTopTextview.text = game?.rating_top.toString()
        holder.gamePlatformsTextview.text = platformtext
        holder.gameGenresTextview.text = genresText
        Picasso.get().load(game?.background_image).into(holder.gameImageView)

    }
    override fun getItemCount(): Int {
        if (mGames == null) return 0
        return mGames!!.size
    }
    fun setGameData(games: List<Results>){
        mGames = games
        notifyDataSetChanged()
    }
    fun getGameAtPosition(position: Int): Results? {
        return mGames?.get(position)
    }
    fun setOnItemClickListener(clickListener: ClickListener){
        mClickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(v: View, position: Int)
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