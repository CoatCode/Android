package com.junhyuk.daedo.main.bottomItem.home.profile

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import kotlinx.android.synthetic.main.fragment_profile.*
import org.w3c.dom.Comment

//recyclerview adapter
class ProfileRecyclerViewAdapter(private val context: Context, private val postList : ArrayList<UserProfileData>, val view : View?, val userId : Int,
                                 val itemClick: (UserProfileData) -> Unit) : RecyclerView.Adapter<ProfileRecyclerViewAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_post_recyclerview_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(postList[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //유저 게시물1
        private var userProfile1 = itemView.findViewById<ImageView>(R.id.user_post1)
        //유저 게시물2
        private var userProfile2 = itemView.findViewById<ImageView>(R.id.user_post2)
        //유저 게시물3
        private var userProfile3 = itemView.findViewById<ImageView>(R.id.user_post3)


        fun bind (profile: UserProfileData) {
            for (i in profile.image_urls.indices){
                Glide.with(context)
                    .load(profile.image_urls[i])
                    .into(userProfile1)
//                Glide.with(context)
//                    .load(profile.image_urls[i+1])
//                    .into(userProfile2)
//                Glide.with(context)
//                    .load(profile.image_urls[i+2])
//                    .into(userProfile3)
                Log.d("url","url" + profile.image_urls)
            }

        }
    }
}