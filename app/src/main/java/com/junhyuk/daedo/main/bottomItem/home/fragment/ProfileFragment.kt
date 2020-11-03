package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.GetCommentList
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.ItemSize
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import com.junhyuk.daedo.main.bottomItem.comment.writeComment.SendWriteComment
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_comment.view.*

class ProfileFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activityBox = activity
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        return view
    }
}