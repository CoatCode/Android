                        package com.junhyuk.daedo.main.bottomItem.comment.getCommentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import com.junhyuk.daedo.main.bottomItem.comment.writeComment.SendWriteComment
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_comment.view.*

class CommentFragment : Fragment() {
    private val sendComment = SendWriteComment()
    private val getComment = GetCommentList()

    private companion object

    var commentList = arrayListOf<CommentData>()
    private var comment: String = ""
    private lateinit var mAdapter: CommentRecyclerviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activityBox = activity
        val view = inflater.inflate(R.layout.fragment_comment, container, false)
        mAdapter = CommentRecyclerviewAdapter(requireContext(), commentList,view) {

        }
        //댓글 목록 받아오는 클래스로 값 전달
        getComment.getCommentList(
            activityBox!!.application,
            mAdapter,
            commentList
        )
        view.comment_recycler_view?.adapter = mAdapter
        view.comment_recycler_view.setHasFixedSize(true)

        val lm = LinearLayoutManager(context)
        view.comment_recycler_view.layoutManager = lm
        view.comment_recycler_view.addItemDecoration(ItemSize(100))
        //댓글 작성 버튼 누를 시 SendComment 클래스 호출하고 입력받은 댓글을 넘겨준다
        view.write_comment_button?.setOnClickListener {
            //작성한 댓글 변수에 저장장
            comment = this.edit_comment.text.toString()

            //작성한 댓글 서버통신 클래스로 값 전달
            sendComment.sendComment(comment, activityBox.application)

        }

        return view
    }


}