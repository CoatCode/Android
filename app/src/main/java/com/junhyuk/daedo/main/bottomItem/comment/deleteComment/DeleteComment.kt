package com.junhyuk.daedo.main.bottomItem.comment.deleteComment

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import com.junhyuk.daedo.main.bottomItem.comment.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//댓글 삭제 클래스
class DeleteComment {
    private var commentList = arrayListOf<CommentData>(
    )

    internal fun deleteComment(
        getApplication: Application,
        commentId : String,
        cAdapter : CommentRecyclerviewAdapter

    ) {
        //AccessToken
        val token: String = EmailLoginBody.instance!!.access_token
        val postId : String = ""
        (getApplication as DaedoApplication).retrofit.create(CommentInterface::class.java)
            .deleteComment(postId,commentId,"Bearer $token")
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    //댓글 목록 삭제
                    commentList.clear()
                    //댓글 목록 추가
                    commentList.addAll(commentList)
                    //댓글 목록 갱신
                    cAdapter.notifyDataSetChanged()

            }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("commentFa", "commentFa")
            }

        })

    }


}


