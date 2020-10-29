package com.junhyuk.daedo.main.bottomItem.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.activity.MainActivity
import com.junhyuk.daedo.main.bottomItem.home.data.FeedData
import com.junhyuk.daedo.main.bottomItem.home.data.PostId
import com.junhyuk.daedo.main.bottomItem.home.fragment.FeedDetailFragment
import com.junhyuk.daedo.main.bottomItem.home.module.FeedTime
import com.junhyuk.daedo.main.bottomItem.home.module.LikeClickModule
import com.junhyuk.daedo.main.bottomItem.home.module.LikeModule
import com.junhyuk.daedo.main.bottomItem.home.paging.DiffUtilCallBack
import kotlinx.android.synthetic.main.cardview_feed_item.view.*


class FeedAdapter(private val context: Context, private val activity: FragmentActivity) :
    PagedListAdapter<FeedData, FeedAdapter.Holder>(DiffUtilCallBack()) {

    private val feedPostTime = FeedTime()
    private val likeClickModule = LikeClickModule()
    private val likeModule = LikeModule()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_feed_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val feedData = getItem(position)

        holder.itemView.setOnClickListener {
            PostId.postId = feedData?.id!!
            val navController: NavController = Navigation.findNavController(it)
            navController.navigate(R.id.action_navigation_home_to_feedDetailFragment)
        }

        likeModule.likeModule(context, feedData?.id!!, holder)

        holder.heartButton.setOnClickListener {
            likeClickModule.likeClickModule(context, feedData.id, holder)
        }

        Glide.with(context)
            .load(feedData.owner.image)
            .override(100)
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

        when {
            feedData.comment_count <= 0 -> {
                holder.commentView.visibility = View.GONE
                holder.commentView2.visibility = View.GONE
            }
            feedData.comment_count == 1 -> {
                holder.commentView.visibility = View.VISIBLE
                Glide.with(context)
                    .load(feedData.comment_preview[0].owner.image)
                    .transform(CenterCrop(), RoundedCorners(1000000))
                    .into(holder.commentProfile)
                holder.commentName.text = feedData.comment_preview[0].owner.username
                holder.commentText.text = feedData.comment_preview[0].content
            }
            feedData.comment_count > 1 -> {

                holder.commentView.visibility = View.VISIBLE
                holder.commentView2.visibility = View.VISIBLE

                Glide.with(context)
                    .load(feedData.comment_preview[0].owner.image)
                    .transform(CenterCrop(), RoundedCorners(1000000))
                    .into(holder.commentProfile)
                holder.commentName.text = feedData.comment_preview[0].owner.username
                holder.commentText.text = feedData.comment_preview[0].content

                Glide.with(context)
                    .load(feedData.comment_preview[1].owner.image)
                    .transform(CenterCrop(), RoundedCorners(1000000))
                    .into(holder.commentProfile2)
                holder.commentName2.text = feedData.comment_preview[1].owner.username
                holder.commentText2.text = feedData.comment_preview[1].content

            }
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
        var commentProfile: ImageView = itemView.commentProfile
        var commentProfile2: ImageView = itemView.commentProfile2
        var commentName: TextView = itemView.commentName
        var commentName2: TextView = itemView.commentName2
        var commentText: TextView = itemView.commentContent
        var commentText2: TextView = itemView.commentContent2
        var commentView: ConstraintLayout = itemView.commentView
        var commentView2: ConstraintLayout = itemView.commentView2
        var heartButton: ImageButton = itemView.heart
    }

}