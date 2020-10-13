package com.junhyuk.daedo.main.bottomItem.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.getCommentList.CommentData
import com.junhyuk.daedo.feed.getCommentList.GetCommentList
import com.junhyuk.daedo.feed.getCommentList.PersonAdapter
import com.junhyuk.daedo.feed.writeComment.SendWriteComment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    var sb : String = "asd"
    private var personList = arrayListOf<CommentData>(
        //recyclerview 에 담길 값
        CommentData(sb, "애플망해라"),
        CommentData("강민석", "애플웩"),
        CommentData("강민석", "애플구려"),
        CommentData("강민석", "애플별로야"),
        CommentData("봉화왕자", "봉화에도 애플스토어를!"),
        CommentData("강민석", "애플 비싸"),
        CommentData("강민석", "삼성좋아"),
        CommentData("강민석", "아이폰 구려"),
        CommentData("강민석", "갤럭시 s20살래")
    )

    private var comment: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val lm = LinearLayoutManager(context)
        view.comment_recycler_view.adapter = PersonAdapter(requireContext(), personList)
        view.comment_recycler_view.setHasFixedSize(true)
        view.comment_recycler_view.layoutManager = lm

        val sendComment = SendWriteComment()
        val getComment = GetCommentList()
        val ct: FragmentActivity? = activity
        //댓글 작성 버튼 누를 시 SendComment 클래스 호출하고 입력받은 댓글을 넘겨준다
        view.write_comment?.setOnClickListener {
            comment = this.edit_comment.text.toString()
            Log.d("test", "test:$comment")
            sendComment.sendComment(comment, ct!!.application)
            getComment.getCommentList(ct.application)
            }


            return view
        }

    }
