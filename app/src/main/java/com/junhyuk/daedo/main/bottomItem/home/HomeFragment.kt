package com.junhyuk.daedo.main.bottomItem.home

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.getCommentList.GetCommentList
import com.junhyuk.daedo.feed.getCommentList.PersonAdapter
import com.junhyuk.daedo.feed.getCommentList.SchoolData
import com.junhyuk.daedo.feed.writeComment.SendWriteComment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private val sendComment = SendWriteComment()
    private val getComment = GetCommentList()
    private val activityBox = Application()
    private var personList = arrayListOf<SchoolData>()
    private var comment: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

          val mAdapter = PersonAdapter(requireContext(),personList)
          val view = inflater.inflate(R.layout.fragment_home, container, false)
          val lm = LinearLayoutManager(context)
          view.comment_recycler_view.adapter = mAdapter
          view.comment_recycler_view.setHasFixedSize(true)
          view.comment_recycler_view.layoutManager = lm

        //댓글 작성 버튼 누를 시 SendComment 클래스 호출하고 입력받은 댓글을 넘겨준다
        view.write_comment?.setOnClickListener {
            Log.d("HomeFragment","HomeFragment")
            comment = this.edit_comment.text.toString()
            Log.d("test", "test:$comment")
            sendComment.sendComment(comment,activityBox)
            getComment.getCommentList(activityBox,requireContext())
            }
            return view
        }

    }

