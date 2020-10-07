package com.junhyuk.daedo.main.bottomItem.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.comment.SendComment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment() : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var comment: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val sendComment = SendComment()

        val context: FragmentActivity? = activity

        root.write_comment?.setOnClickListener {
            comment = this.edit_comment.text.toString()
            Log.d("test","test$comment")
            sendComment?.sendComment(comment, context!!.application)

        }






        return root
    }

}