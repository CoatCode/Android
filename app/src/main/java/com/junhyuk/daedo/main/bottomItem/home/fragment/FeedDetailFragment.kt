package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentFragment
import com.junhyuk.daedo.main.bottomItem.home.profile.GetUserProfile
import kotlinx.android.synthetic.main.fragment_feed_detail_item.view.*


class FeedDetailFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val applicationBox = activity
        val getUserProfile = GetUserProfile()
        val view = inflater.inflate(R.layout.fragment_feed_detail_item, container, false)
        view?.profile?.setOnClickListener {
            Log.d("test","test")
            getUserProfile.getUserProfile(applicationBox!!.application)
        }
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.add(R.id.commentFragment, CommentFragment())!!.commit()

        return view

    }


}