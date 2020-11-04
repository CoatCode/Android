package com.junhyuk.daedo.main.bottomItem.home.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.home.data.ImageData
import kotlinx.android.synthetic.main.cardview_post_image.view.*

class ImageSliderAdapter(private var images: ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<ImageSliderAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewpager_image, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(context)
            .load(images[position])
            .into(holder.imagesItem)

        holder.imagesItem.setOnClickListener{
            ImageData.image = images[position]
            val navController: NavController = Navigation.findNavController(it)
            navController.navigate(R.id.action_feedDetailFragment_to_imageDetailFragment)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagesItem: ImageView = itemView.findViewById(R.id.imageSliderItem)
    }

}