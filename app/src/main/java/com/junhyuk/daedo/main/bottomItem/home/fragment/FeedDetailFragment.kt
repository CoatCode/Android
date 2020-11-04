package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentFragment
import com.junhyuk.daedo.main.bottomItem.home.adapter.ImageSliderAdapter
import com.junhyuk.daedo.main.bottomItem.home.data.FeedDetailData
import com.junhyuk.daedo.main.bottomItem.home.profile.GetUserProfile
import kotlinx.android.synthetic.main.fragment_feed_detail_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder


class FeedDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val applicationBox = activity
        val getUserProfile = GetUserProfile()
        val view = inflater.inflate(R.layout.fragment_feed_detail_item, container, false)

        Glide.with(requireActivity().applicationContext)
            .load(FeedDetailData.feedData.owner.image)
            .override(100)
            .transform(CenterCrop(), RoundedCorners(1000000))
            .into(view.profile)


        val adapter = ImageSliderAdapter(
            FeedDetailData.feedData.image_urls,
            requireActivity().applicationContext
        )

        view.feedImage.adapter = adapter

        view.imageSlider.setViewPager(view.feedImage)

        view.title.text = FeedDetailData.feedData.title
        view.contentText.text = FeedDetailData.feedData.content
        val stringBuilder = StringBuilder("")
        if (!(FeedDetailData.feedData.tag.isNullOrEmpty())) {
            FeedDetailData.feedData.tag.forEach {
                stringBuilder.append(it)
            }
        }
        view.hashTag.text = stringBuilder

        var callUserInformation: Int
        CoroutineScope(Dispatchers.IO).launch {
            callUserInformation = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.id

            Log.d("userId", "userId1 : $callUserInformation")
            withContext(Dispatchers.Main) {
                Log.d("userId", "userId : $callUserInformation")

                view?.profile?.setOnClickListener {
                    Log.d("test", "test")
                    getUserProfile.getUserProfile(applicationBox!!.application, callUserInformation)
                }
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.add(R.id.commentFragment, CommentFragment())!!.commit()

            }
        }

        return view
    }
}