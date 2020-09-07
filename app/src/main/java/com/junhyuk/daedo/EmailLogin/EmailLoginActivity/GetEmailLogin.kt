package com.junhyuk.daedo.EmailLogin.EmailLoginActivity

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Oauth.Oauth
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import com.junhyuk.daedo.EmailLogin.UserDataHandle.UserDataActivity
import com.junhyuk.daedo.Main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetEmailLogin {
    //네트워크 작업
    internal fun getEmailLogin(
        email: String,
        password: String,
        getApplication: Application,
        context: Context

    ) {
        //Oauth 2.0
        val api = Oauth.getInstance()
        val getUser = UserDataActivity()
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
                    //다음 화면으로 이동
                    val intent = Intent(context, MainActivity::class.java)
                    //Dialog창 로그인 시도시 결과에 따라 다른 dialog가 뜬다
                    loginDialog.connectionSuccess(
                        response.code(),
                        context,
                        response.errorBody()?.string().toString(),
                        intent,
                        sweetAlertDialog
                    )

                    //통신성공
                    if (response.code() == 200) {

                        //서버로부터 받은 정보들을 EmailLoginBody 변수에 담아준다
                        EmailLoginBody.instance = response.body()
                        Log.d("toklen", "token" + EmailLoginBody.instance)
                        getUser.GetUserData(getApplication, context)

                    }
                    //통신 실패
                    else if (response.code() == 401) {
                        Log.d("tttt", "ttttt" + response.errorBody())

                    }

                }

                //서버와 연결 실패
                override fun onFailure(call: Call<EmailLoginBody>, t: Throwable) {
                    Log.d("s", "s")
                    //LoginDialog를 호출하여 서버와의 연결 실패를 dialog로 띄운다
                    loginDialog.connectionFail(context, sweetAlertDialog)
                }


            })
    }


}