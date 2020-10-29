package com.junhyuk.daedo.main.bottomItem.comment.getCommentList

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import com.junhyuk.daedo.main.bottomItem.comment.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//댓글 목록을 받아오는 클래스
class GetCommentList {
    private var personListTmp = arrayListOf<CommentData>(
    )

    internal fun getCommentList(
        getApplication: Application,
        cAdapter: CommentRecyclerviewAdapter,
        commentList : ArrayList<CommentData>
    ) {
        //AccessToken 변수에 저장
        val token: String =
            com.junhyuk.daedo.emailLogin.server.EmailLoginBody.instance!!.access_token
        val postId : String = ""
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
                        //댓글 목록 초기화
                        commentList.clear()
                        //댓글 목록에 추가
                        commentList.addAll(personListTmp)
                        //댓글 목록 업데이트
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


