 package com.junhyuk.daedo.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.deleteComment.DeleteComment
import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*

 /*
 * 하단 슬라이드뷰 fragment
 *
 *
 */
class BottomSheetDialog(Comment : CommentData) : BottomSheetDialogFragment() {
     private val deleteComment = DeleteComment()
     private val example = Comment.comment_id
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityBox = requireActivity()
        //댓글 수정 버튼
        view.comment_correct.setOnClickListener {

        }
        //댓글 삭제 버튼
        view.comment_delete.setOnClickListener {
               deleteComment.deleteComment(activityBox.application, example)
        }
    }
}