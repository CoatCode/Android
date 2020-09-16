package com.junhyuk.daedo.Main.ui.post.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.junhyuk.daedo.Main.ui.post.Data.ImageListData
import com.junhyuk.daedo.R

class PostImageAdapter : RecyclerView.Adapter<PostImageAdapter.Holder>() {

    private var arrayList: ArrayList<ImageListData> = ArrayList()

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setList(context: Context){
        val drawable: Drawable? = context.resources.getDrawable(R.drawable.ic_vector, null)
        this.arrayList[0].imageList.add(drawable!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_post_image, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(arrayList[0])
    }

    override fun getItemCount(): Int {
        return if (arrayList.size == 0){
            0
        }
        else{
            arrayList[0].imageList.size
        }
    }



    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(imageListData: ImageListData){

        }
    }


}