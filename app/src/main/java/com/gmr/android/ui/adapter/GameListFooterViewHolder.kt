package com.gmr.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmr.android.R
import com.gmr.android.data.NetworkState
import kotlinx.android.synthetic.main.network_state_item.view.*

class GameListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view){

    fun bind(status: NetworkState?) {
        itemView.footer_network_state_progressBar.visibility =
            if (status == NetworkState.LOADING) View.VISIBLE else View.INVISIBLE
        itemView.footer_retry_button.visibility =
            if (status == NetworkState.ERROR) View.VISIBLE else View.INVISIBLE
        itemView.footer_error_message_textView.visibility =
            if (status == NetworkState.ERROR) View.VISIBLE else View.INVISIBLE
        itemView.footer_error_message_textView.setText(R.string.no_connection)
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): GameListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.network_state_item, parent, false)
            view.footer_retry_button.setOnClickListener { retry() }
            return GameListFooterViewHolder(view)
        }
    }
}