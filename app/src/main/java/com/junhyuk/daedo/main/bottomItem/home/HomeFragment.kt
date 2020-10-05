package com.junhyuk.daedo.main.bottomItem.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.comment.SendComment
import com.junhyuk.daedo.feed.comment.SendCommentActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var comment: String = ""
    private val sendComment = SendComment()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.write_comment.setOnClickListener {
            Log.d("plz","plz")
                comment = this.edit_comment.text.toString()
                (activity as SendCommentActivity)
                Log.d("test","test")
                //sendComment.sendComment(comment, Application(),requireContext())
                Log.d("comment","comment:$comment")
        }






        return root
    }
}