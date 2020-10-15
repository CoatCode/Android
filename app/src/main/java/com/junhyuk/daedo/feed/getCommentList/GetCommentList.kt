package com.junhyuk.daedo.feed.getCommentList

import android.app.Application
import android.content.Context
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.feed.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCommentList {
    private var personListTmp = arrayListOf<SchoolData>(
    )

    internal fun getCommentList(
        getApplication: Application,
        context: Context,
        cAdapter: PersonAdapter,
        personList : ArrayList<SchoolData>
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

                override fun onFailure(call: Call<ArrayList<SchoolData>>, t: Throwable) {
                    Log.d("fa", "fa")
                }
            })
    }
}


