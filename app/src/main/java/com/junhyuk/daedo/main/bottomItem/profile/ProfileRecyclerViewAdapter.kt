package com.junhyuk.daedo.main.bottomItem.profile

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junhyuk.daedo.R

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
        /*val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 50
        holder.itemView.requestLayout()*/
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //유저 게시물1
        private var userPost = itemView.findViewById<ImageView>(R.id.user_post1)

        fun bind (profile: UserProfileData) {
            for (i in profile.image_urls.indices){
                Glide.with(context)
                    .load(profile.image_urls[i])
                    .centerCrop()
                    .into(userPost)
            }
            userPost.setOnClickListener {

            }
        }
    }
}