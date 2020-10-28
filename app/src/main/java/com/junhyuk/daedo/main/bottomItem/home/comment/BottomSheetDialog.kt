package com.junhyuk.daedo.main.bottomItem.home.comment

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.home.comment.deleteComment.DeleteComment
import com.junhyuk.daedo.main.bottomItem.home.comment.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.main.bottomItem.home.comment.getCommentNetwork.CommentData
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*


// 하단 슬라이드뷰 fragment
class BottomSheetDialog(Comment: CommentData, view: View?, mAdapter : CommentRecyclerviewAdapter) : BottomSheetDialogFragment() {
    //댓글 삭제 서버 통신 클래스
    private val deleteComment = DeleteComment()
    //댓글 ID
    private val commentId = Comment.comment_id
    //fragmentHomeView view 변수
    private val fragmentHomeView = view
    //서버에서 받아온 값을 담아주는 data class
    private val comment = Comment
    //CommentRecyclerViewAdapter
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
            //bottomSheet 호출
            bottomSheet.deleteComment.deleteComment(activityBox.application,commentId,adapter)
            val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(editComment?.windowToken, 0)
            if (dia != null) {
                dismissAllowingStateLoss()
            }
        }
    }
}