package com.junhyuk.daedo.feed.deleteComment

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.feed.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DeleteComment {

    internal fun deleteComment(
        getApplication: Application,
        commentId : String

    ) {
        val token: String = EmailLoginBody.instance!!.access_token
        Log.d("example","example : $commentId")

        //val example = cId.comment_id

        (getApplication as DaedoApplication).retrofit.create(CommentInterface::class.java)
        //    .deleteComment("Bearer $token", commentId)
            (object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("delete", "delete")
            }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("commentFa", "commentFa")
            }

        })

    }


}


