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
            .enqueue(object : Callback<ArrayList<SchoolData>> {
                override fun onResponse(
                    call: Call<ArrayList<SchoolData>>,
                    response: Response<ArrayList<SchoolData>>
                ) {
                    if (response.code() == 200){
                        Log.d("200","200"+response.body())
                    }
                    if (response.code() == 401){
                        Log.d("401","401"+response.errorBody())
                    }
                }

                override fun onFailure(call: Call<ArrayList<SchoolData>>, t: Throwable) {
                    Log.d("fa","fa")
                }

            })
    }
}


