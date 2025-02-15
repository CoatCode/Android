package com.junhyuk.daedo.main.bottomItem.comment.writeComment

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.main.bottomItem.comment.network.CommentInterface
import com.junhyuk.daedo.main.bottomItem.comment.writeCommentNetwork.WriteCommentBody
import com.junhyuk.daedo.main.bottomItem.home.data.PostId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SendWriteComment {
    //네트워크 작업
    internal fun sendComment(
        comment: String,
        getApplication: Application
    ) {
        //로그인 할 때 받아놓은 access token
        val token : String = EmailLoginBody.instance!!.access_token
        val postId : Int = PostId.postId
        //DaedoApplication 호출
        (getApplication as DaedoApplication).retrofit.create(CommentInterface::class.java)
                //작성한 댓글과 token 서버로 전송
                .sendComment(postId,"Bearer $token", WriteCommentBody(comment))
                .enqueue(object : Callback<WriteCommentBody> {
                    override fun onResponse(
                        call: Call<WriteCommentBody>,
                        response: Response<WriteCommentBody>
                    ) {
                        //통신성공
                        if (response.code() == 201) {
                            Log.d("sendComment", "sendComment")
                        }
                        //통신 실패
                        else if (response.code() == 400) {
                            Log.d("error", "error:" + response.errorBody())
                        }
                    }

                    //서버와 연결 실패
                    override fun onFailure(call: Call<WriteCommentBody>, t: Throwable) {
                    }
                })
    }


}