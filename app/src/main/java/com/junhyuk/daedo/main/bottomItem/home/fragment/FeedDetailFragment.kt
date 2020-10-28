package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.home.comment.getCommentList.CommentFragment


class FeedDetailFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_feed_detail_item, container, false)

        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.add(R.id.commentFragment, CommentFragment())!!.commit()

        return view

    }


}