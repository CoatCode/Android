package com.junhyuk.daedo.main.bottomItem.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.junhyuk.daedo.R

class FeedAdapter(private var feedArrayList: ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<FeedAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_feed_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {



    }

    override fun getItemCount(): Int {
        return feedArrayList.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }

}