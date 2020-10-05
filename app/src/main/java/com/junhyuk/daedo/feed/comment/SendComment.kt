package com.junhyuk.daedo.feed.comment

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.dataBase.userDataHandler.UserDataActivity
import com.junhyuk.daedo.emailLogin.emailLoginActivity.LoginDialog
import com.junhyuk.daedo.feed.commentServer.Comment
import com.junhyuk.daedo.feed.commentServer.CommentBody
import com.junhyuk.daedo.main.activity.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SendComment {
    //네트워크 작업
    internal fun sendComment(
        comment: String,
        getApplication: Application,
        context: Context

    ) {
        Log.d("rrr","rrr")
        //유저 정보를 담는 activity
        val getUser = UserDataActivity()
        //dialog 변수
        val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
        sweetAlertDialog
            .setTitleText("로딩 중")
            .setCancelable(false)
        sweetAlertDialog.show()

        val sendCommentService =
            (getApplication as DaedoApplication).retrofit.create(Comment::class.java)
            sendCommentService.sendComment("",comment)

            .enqueue(object : Callback<CommentBody> {
                val loginDialog = LoginDialog()
                override fun onResponse(
                    call: Call<CommentBody>,
                    response: Response<CommentBody>

                ) {
                    Log.d("test","test")
                    //다음 화면으로 이동
                    val intent = Intent(context, MainActivity::class.java)
                    //로그인 시도시 결과에 따라 다른 dialog 가 뜬다
                    loginDialog.connectionSuccess(
                        response.code(),
                        context,
                        response.errorBody()?.string().toString(),
                        intent,
                        sweetAlertDialog
                    )

                    //통신성공
                    if (response.code() == 200) {

                    }
                    //통신 실패
                    else if (response.code() == 401) {

                    }

                }
                //서버와 연결 실패
                override fun onFailure(call: Call<CommentBody>, t: Throwable) {
                    //LoginDialog 를 호출하여 서버와의 연결 실패를 dialog 로 띄운다
                    loginDialog.connectionFail(context, sweetAlertDialog)
                }


            })
    }


}