package com.junhyuk.daedo.main.bottomItem.post.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import kotlinx.android.synthetic.main.cardview_post_image.view.*

class PostImageAdapter(private var postArrayList: ArrayList<Uri>, private val context: Context) :
    RecyclerView.Adapter<PostImageAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_post_image, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        //화면에 이미지 표시
        Glide.with(context)
            .load(postArrayList[position])
            .thumbnail(Glide.with(context).load(R.raw.loading))
            .transform(CenterCrop(), RoundedCorners(10))
            .into(holder.imageViewList)

    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewList: ImageView = itemView.postImage
    }

}