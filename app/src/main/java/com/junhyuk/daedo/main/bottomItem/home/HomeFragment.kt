package com.junhyuk.daedo.main.bottomItem.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.getCommentList.CommentData
import com.junhyuk.daedo.feed.getCommentList.PersonAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
//    var photo : String? = Owner.instance!!.profile
//    var name : String? = Owner.instance!!.username
////    var email : String? = Owner.instance!!.email
    private var personList = arrayListOf<CommentData>(
//        //recyclerview 에 담길 값
        CommentData("강민석", "애플"),
        CommentData("강민석", "애플"),
        CommentData("강민석", "애플"),
        CommentData("강민석", "애플")
    )

  //  private var comment: String = ""

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

//        val sendComment = SendWriteComment()
//        val getComment = GetCommentList()
//        val ct: FragmentActivity? = activity
        //댓글 작성 버튼 누를 시 SendComment 클래스 호출하고 입력받은 댓글을 넘겨준다
//        //view.write_comment?.setOnClickListener {
//            comment = this.edit_comment.text.toString()
//            Log.d("test","test:$comment")
//            sendComment.sendComment(comment, ct!!.application)
//            getComment.getCommentList(ct.application)
//        //}


        return view
    }

}