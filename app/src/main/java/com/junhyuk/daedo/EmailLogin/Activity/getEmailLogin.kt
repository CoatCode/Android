package com.junhyuk.daedo.EmailLogin.Activity

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Oauth.Oauth
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import com.junhyuk.daedo.Main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class getEmailLogin {
    //네트워크 작업
    internal fun getEmailLogin(
        email: String,
        password: String,
        getApplication: Application,
        context: Context

    ){
        //Oauth 2.0
        val api = Oauth.getInstance()
        val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
        sweetAlertDialog
            .setTitleText("로딩 중")
            .setCancelable(false)
        sweetAlertDialog.show()

        (getApplication as DaedoApplication)
            .requestService()
        api.sendLoginInformation(EmailLoginBody(email, password))

            .enqueue(object : Callback<EmailLoginBody> {
                val loginDialog = LoginDialog()
                override fun onResponse(
                    call: Call<EmailLoginBody>,
                    response: Response<EmailLoginBody>

                ) {
                    //통신성공
                    if (response.code() == 200) {
                            //서버로부터 받은 정보들을 EmailLoginBody 변수에 담아준다
                            EmailLoginBody.instance = response.body()
                        val intent = Intent(context, MainActivity::class.java)
                        //LoginDialog를 호출하여 로그인 성공 dialog를 뜨운다
                       loginDialog.connectionSuccess(
                            response.code(),
                            context,
                            response.errorBody()?.string().toString(),
                            intent,
                            sweetAlertDialog
                       )
                    }
                    //통신 실패
                    else if (response.code() == 401) {
                        val intent = Intent(context, EmailLoginActivity::class.java)
                        //LoginDialog를 호출하여 로그인 실패 dialog를 띄운다
                        loginDialog.connectionSuccess(
                            response.code(),
                            context,
                            response.errorBody()?.string().toString(),
                            intent,
                            sweetAlertDialog
                        )

                    }

                }

                //서버와 연결 실패
                override fun onFailure(call: Call<EmailLoginBody>, t: Throwable) {
                Log.d("s","s")
                    //LoginDialog를 호출하여 서버와의 연결 실패를 dialog로 띄운다
                    loginDialog.connectionFail(context, sweetAlertDialog)
                }


            })
    }




}