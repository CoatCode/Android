package com.junhyuk.daedo.feed

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.correctComment.CorrectComment
import com.junhyuk.daedo.feed.correctComment.CorrectCommentBody
import com.junhyuk.daedo.feed.deleteComment.DeleteComment
import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*


/*
* 하단 슬라이드뷰 fragment
*
*
*/
class BottomSheetDialog(Comment: CommentData, view: View?) : BottomSheetDialogFragment() {
    private val deleteComment = DeleteComment()
    private val commentId = Comment.comment_id
    private val commentContent = Comment.content
    private val fragmentHomeView = view
    private val comment = Comment
    private val correctComment = CorrectComment()



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
        val editComment: EditText? = fragmentHomeView?.findViewById(R.id.edit_comment)
        val activityBox = requireActivity()
        val bottomSheet = BottomSheetDialog(comment, view)
        val dia = dialog


        //댓글 수정 버튼
        view.comment_correct.setOnClickListener {
            correctComment.correctComment(activityBox.application,commentId, commentContent)
            //editComment?.showKeyboard()
            editComment?.setText(commentContent)!!
            if (dia != null) {
                dismissAllowingStateLoss()
            }
            editComment.showSoftInputOnFocus
        }

        //댓글 삭제 버튼
        view.comment_delete?.setOnClickListener {
            bottomSheet.deleteComment.deleteComment(activityBox.application, commentId)
            val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(editComment?.getWindowToken(), 0)
            if (dia != null) {
                dismissAllowingStateLoss()
            }

        }
    }
    private fun correct(){
        val activityBox = requireActivity()
    //    correctComment.correctComment(activityBox.application,commentId)
    }

}