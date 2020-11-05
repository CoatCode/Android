package com.junhyuk.daedo.main.bottomItem.home.profile

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData

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
        //recyclerview item 간격 조정 코드
//        val layoutParams = holder.itemView.layoutParams
//        layoutParams.height = layoutParams.height
//        holder.itemView.requestLayout()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //유저 게시물1
        private val userProfile1 = itemView.findViewById<ImageView>(R.id.user_post1)
        //유저 게시물2
        private val userProfile2 = itemView.findViewById<TextView>(R.id.user_post2)
        //유저 게시물3
        private val userProfile3 = itemView.findViewById<TextView>(R.id.user_post3)


        fun bind (profile: UserProfileData) {

        }
    }
}