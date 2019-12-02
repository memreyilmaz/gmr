package com.gmr.android.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmr.android.data.*

class GamesListAdapter(private val retry: () -> Unit) : PagedListAdapter<Results,
        RecyclerView.ViewHolder>(GamesDiffCallback) {

    private lateinit var listener: ClickListener
    private var status = NetworkState.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) GameItemViewHolder.create(parent)
        else GameListFooterViewHolder.create(
            retry,
            parent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as GameItemViewHolder).bind(getItem(position))
        else (holder as GameListFooterViewHolder).bind(status)
        if (holder is GameItemViewHolder){
            holder.itemView.setOnClickListener { v ->
                listener.onItemClick(v, holder.layoutPosition)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (status == NetworkState.LOADING || status == NetworkState.ERROR)
    }

    fun setState(status : NetworkState) {
        this.status = status
        notifyItemChanged(super.getItemCount())
    }

    fun getGameAtPosition(position: Int): Results? {
        return currentList?.get(position)
    }

    fun setOnItemClickListener(clickListener: ClickListener){
        listener = clickListener
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