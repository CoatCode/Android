package com.junhyuk.daedo.feed.getCommentList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.junhyuk.daedo.R
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*

/*
* 하단 슬라이드뷰 fragment
*
*
*/
class BottomSheetDialog : BottomSheetDialogFragment() {
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
        view.comment_correct.setOnClickListener {
            Log.d("correct","correct")
        }
        view.comment_delete.setOnClickListener {
        }
    }
}