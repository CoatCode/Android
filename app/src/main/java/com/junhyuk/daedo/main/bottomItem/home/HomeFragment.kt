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
//    var photo : String? = Owner.instance!!.profile
//    var name : String? = Owner.instance!!.username
////    var email : String? = Owner.instance!!.email
    private var personList = arrayListOf<CommentData>(
//        //recyclerview 에 담길 값
        CommentData("강민석", "애플", "add"),
        CommentData("강민석", "애플", "add"),
        CommentData("강민석", "애플", "좆병신"),
        CommentData("강민석", "애플", "좆병신")
    )

    private lateinit var homeViewModel: HomeViewModel
    private var comment: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.comment_recycler_view_item, container, false)
        val lm = LinearLayoutManager(context)
        view.comment_recycler_view.adapter = PersonAdapter(context!!, personList)
        view.comment_recycler_view.setHasFixedSize(true)
        view.comment_recycler_view.layoutManager = lm

        val sendComment = SendWriteComment()
        val getComment = GetCommentList()
        val ct: FragmentActivity? = activity
        //댓글 작성 버튼 누를 시 SendComment 클래스 호출하고 입력받은 댓글을 넘겨준다
        view.write_comment?.setOnClickListener {
            comment = this.edit_comment.text.toString()
            Log.d("test","test:$comment")
            sendComment.sendComment(comment, ct!!.application)
            getComment.getCommentList(ct.application)
        }


        return view
    }

}