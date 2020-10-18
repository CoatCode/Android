package com.junhyuk.daedo.feed.getCommentList

import android.app.Application
import android.content.Context
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import com.junhyuk.daedo.feed.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCommentList {
    private var personListTmp = arrayListOf<CommentData>(
    )

    internal fun getCommentList(
        getApplication: Application,
        context: Context,
        cAdapter: CommentRecyclerviewAdapter,
        personList : ArrayList<CommentData>
    ) {

        val token: String =
            com.junhyuk.daedo.emailLogin.server.EmailLoginBody.instance!!.access_token
        (getApplication as DaedoApplication).retrofit.create(CommentInterface::class.java)
            .getComment("Bearer $token")
            .enqueue(object : Callback<ArrayList<CommentData>> {
                override fun onResponse(
                    call: Call<ArrayList<CommentData>>,
                    response: Response<ArrayList<CommentData>>
                ) {
                    if (response.code() == 200) {
                        personListTmp = response.body()!!
                        Log.d("responseBody","responseBody${response.body()}")
                        personList.addAll(personListTmp)
                        cAdapter.notifyDataSetChanged()
                    }
                    if (response.code() == 401) {
                        Log.d("401", "401" + response.errorBody())
                    }
                }

                override fun onFailure(call: Call<ArrayList<CommentData>>, t: Throwable) {
                    Log.d("fa", "fa")
                }
            })
    }
}


