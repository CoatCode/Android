package com.junhyuk.daedo.feed.getCommentList

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.feed.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCommentList {
    internal fun getCommentList(
        getApplication: Application
    ) {
        val token: String =
            com.junhyuk.daedo.emailLogin.server.EmailLoginBody.instance!!.access_token

        (getApplication as DaedoApplication).retrofit.create(CommentInterface::class.java)
            .getComment("Bearer $token")
            .enqueue(object : Callback<GetCommentBody> {
                override fun onResponse(
                    call: Call<GetCommentBody>,
                    response: Response<GetCommentBody>

                ) {
                    if (response.code() == 200) {
                        Log.d("Email", "data:${GetCommentBody.instance!!.username}")
                    } else if (response.code() == 401) {
                        Log.d("Email", "data:"+response.errorBody())
                    }
                }
                override fun onFailure(call: Call<GetCommentBody>, t: Throwable) {
                    Log.d("failure", "failure")
                }
            })
    }
}