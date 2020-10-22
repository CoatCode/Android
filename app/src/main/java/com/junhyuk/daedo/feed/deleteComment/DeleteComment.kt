package com.junhyuk.daedo.feed.deleteComment

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.feed.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import com.junhyuk.daedo.feed.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DeleteComment {
    private var personListTmp = arrayListOf<CommentData>(
    )
    
    internal fun deleteComment(
        getApplication: Application,
        commentId : String,
        cAdapter : CommentRecyclerviewAdapter,
        commentList : ArrayList<CommentData>

    ) {
        val token: String = EmailLoginBody.instance!!.access_token

        (getApplication as DaedoApplication).retrofit.create(CommentInterface::class.java)
            .deleteComment(commentId,"Bearer $token")
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("delete", "delete : ${response.code()}")

            }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("commentFa", "commentFa")
            }

        })

    }


}


