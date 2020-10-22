package com.junhyuk.daedo.feed

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.correctComment.CorrectComment
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
    private val correctComment = CorrectComment()
    private val commentId = Comment.comment_id
    private val commentContent = Comment.content
    private val fragmentHomeView = view
    //  private val editComment = editComment


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
        val editComment  : EditText = view.findViewById(R.id.edit_comment)!!
        val activityBox = requireActivity()

        /*    try {
                if(editComment.text.isNotEmpty()){
                    editComment.setText("0");
                    //editComment.setText("0")
                }
            }catch (e: NullPointerException){}*/

        //댓글 수정 버튼
        view.comment_correct.setOnClickListener {
            //  correctComment.correctComment(activityBox.application, commentContent)
            Log.d("keyboard1", "keyboard")
            //    fragmentHomeView.edit_comment.setText(commentContent)!!
            Log.d("keyboard2", "keyboard")
            Log.d("keyboard3", "keyboard")



        }
        //댓글 삭제 버튼
        view.comment_delete.setOnClickListener {
            deleteComment.deleteComment(activityBox.application, commentId)

        }
    }

    private fun EditText.showKeyboard() {
        post {
            requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }

}