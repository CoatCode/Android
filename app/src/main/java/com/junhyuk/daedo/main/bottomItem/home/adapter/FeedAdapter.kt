package com.junhyuk.daedo.main.bottomItem.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.home.data.FeedData
import com.junhyuk.daedo.main.bottomItem.home.module.FeedTime
import com.junhyuk.daedo.main.bottomItem.home.paging.DiffUtilCallBack
import kotlinx.android.synthetic.main.cardview_feed_item.view.*


class FeedAdapter(private val context: Context) : PagedListAdapter<FeedData, FeedAdapter.Holder>(DiffUtilCallBack()) {

    private val feedPostTime = FeedTime()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_feed_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val feedData = getItem(position)

        if(feedData != null){
            Glide.with(context)
                .load(feedData.owner.image)
                .transform(CenterCrop(), RoundedCorners(1000000))
                .into(holder.profileImageView)

            Glide.with(context)
                .load(feedData.image_urls[0])
                .into(holder.feedImage)

            Log.d("feedTime", "data: ${System.currentTimeMillis()}")

            holder.userName.text = feedData.owner.username
            holder.feedTime.text = feedPostTime.calFeedTime(feedData.created_at)
            holder.title.text = feedData.title
            holder.heartCount.text = feedData.like_count.toString()
            holder.commentCount.text = feedData.comment_count.toString()
            holder.viewerCount.text = feedData.view_count.toString()
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImageView: ImageView = itemView.profile
        var userName: TextView = itemView.name
        var feedTime: TextView = itemView.time
        var title: TextView = itemView.title
        var heartCount: TextView = itemView.heartCount
        var commentCount: TextView = itemView.commentCount
        var viewerCount: TextView = itemView.viewerCount
        var feedImage: ImageView = itemView.feedImage
    }

}