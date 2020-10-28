package com.junhyuk.daedo.main.bottomItem.home.comment.getCommentList

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class ItemSize(private val divHeight: Int) : ItemDecoration() {

    private val dh: Int = divHeight


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1)
            outRect.bottom = dh

    }

}