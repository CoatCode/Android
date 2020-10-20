package com.junhyuk.daedo.feed.correctComment

import android.app.Application
import android.util.Log
import android.widget.EditText
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.feed.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CorrectComment {

    internal fun correctComment(
        getApplication: Application,
        commentId : String,
        editText: EditText
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