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
import com.junhyuk.daedo.feed.deleteComment.DeleteComment
import com.junhyuk.daedo.feed.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*


/*
* 하단 슬라이드뷰 fragment
*
*
*/
class BottomSheetDialog(Comment: CommentData, view: View?, mAdapter : CommentRecyclerviewAdapter) : BottomSheetDialogFragment() {
    private val deleteComment = DeleteComment()
    private val commentId = Comment.comment_id
    private val fragmentHomeView = view
    private val comment = Comment
    private val adapter = mAdapter



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
        val bottomSheet = BottomSheetDialog(comment, view, adapter)
        val dia = dialog


        //댓글 삭제 버튼
        view.comment_delete?.setOnClickListener {
            bottomSheet.deleteComment.deleteComment(activityBox.application,commentId,adapter)
            val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(editComment?.windowToken, 0)
            if (dia != null) {
                dismissAllowingStateLoss()
            }
        }
    }
}