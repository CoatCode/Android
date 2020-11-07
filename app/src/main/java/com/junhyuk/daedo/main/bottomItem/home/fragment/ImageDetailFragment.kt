package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.home.data.ImageData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.imageview_feed_image.view.*


class ImageDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.imageview_feed_image, container, false)

        Glide.with(requireContext())
            .load(ImageData.image)
            .into(view.imageDetail)

        requireActivity().nav_view.visibility = View.GONE

        return view

    }
}