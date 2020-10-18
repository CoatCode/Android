package com.junhyuk.daedo.main.bottomItem.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.feed.getCommentList.GetCommentList
import com.junhyuk.daedo.feed.getCommentList.ItemSize
import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import com.junhyuk.daedo.feed.writeComment.SendWriteComment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private val sendComment = SendWriteComment()
    private val getComment = GetCommentList()

    private companion object

    var personList =
        arrayListOf<CommentData>()
    private var comment: String = ""
    private lateinit var mAdapter: CommentRecyclerviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAdapter = CommentRecyclerviewAdapter(requireContext(), personList) {
            //bottomSheetDialog 호출 recyclerview 안 버튼 클릭시 bottomSheetDialog 가 호출 된다.
            val bottomSheet = com.junhyuk.daedo.feed.getCommentList.BottomSheetDialog()
            bottomSheet.show(childFragmentManager,bottomSheet.tag)
        }
       // mAdapter = CommentRecyclerviewAdapter(requireContext(), personList)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.comment_recycler_view?.adapter = mAdapter
        view.comment_recycler_view.setHasFixedSize(true)

        val activityBox = activity

        val lm = LinearLayoutManager(context)
        view.comment_recycler_view.layoutManager = lm
        view.comment_recycler_view.addItemDecoration(ItemSize(100));

        //댓글 작성 버튼 누를 시 SendComment 클래스 호출하고 입력받은 댓글을 넘겨준다
        view.write_comment?.setOnClickListener {
            Log.d("HomeFragment", "HomeFragment")
            comment = this.edit_comment.text.toString()
            Log.d("test", "test:$comment")

            sendComment.sendComment(comment, activityBox!!.application)
            getComment.getCommentList(
                activityBox.application,
                requireContext(),
                mAdapter,
                personList
            )
        }
        return view
    }

}

