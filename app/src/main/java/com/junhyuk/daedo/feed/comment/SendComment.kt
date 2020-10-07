package com.junhyuk.daedo.feed.comment

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.dataBase.userDataHandler.UserDataActivity
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.feed.commentServer.Comment
import com.junhyuk.daedo.feed.commentServer.CommentBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SendComment {
    //네트워크 작업
    internal fun sendComment(
        comment: String,
        getApplication: Application
    ) {
        //유저 정보를 담는 activity
        val getUser = UserDataActivity()
        val token : String = EmailLoginBody.instance!!.access_token
        //dialog 변수

        val sendCommentService =
            (getApplication as DaedoApplication).retrofit.create(Comment::class.java)
                .sendComment("Bearer $token",CommentBody(comment))
                .enqueue(object : Callback<CommentBody> {
                override fun onResponse(
                    call: Call<CommentBody>,
                    response: Response<CommentBody>

                ) {


                    //통신성공
                    if (response.code() == 200) {
                        Log.d("success","success"+response.body())
                    }
                    //통신 실패
                    else if (response.code() == 400) {
                        Log.d("error","error"+response.errorBody())
                    }

                }
                //서버와 연결 실패
                override fun onFailure(call: Call<CommentBody>, t: Throwable) {
                    //LoginDialog 를 호출하여 서버와의 연결 실패를 dialog 로 띄운다
                }
            })
    }


}