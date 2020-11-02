package com.junhyuk.daedo.signUp.workingRetrofit

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.intro.Intro
import com.junhyuk.daedo.signUp.activity.SignUpDialog
import com.junhyuk.daedo.signUp.server.SignUpResponse
import com.junhyuk.daedo.signUp.server.SignUpService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class SetupRetrofit {
    //네트워크 작업
    internal fun setupRetrofit(
        email: String,
        password: String,
        userName: String,
        profile: RequestBody?,
        imageName: String,
        getApplication: Application,
        context: Context
    ) {

        //로딩 다이얼로그
        val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
        sweetAlertDialog
            .setTitleText("로딩 중")
            .setCancelable(false)
        sweetAlertDialog.show()

        //signUp 서비스
        val signUpService =
            (getApplication as DaedoApplication).retrofit.create(SignUpService::class.java)

        //서버에 보낼 데이터
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        builder.addFormDataPart("email", email)
        builder.addFormDataPart("password", password)
        builder.addFormDataPart("username", userName)

        if (profile != null){
            builder.addFormDataPart("profile", imageName, profile)
        }

        val signUpBody: RequestBody = builder.build()

        //signUp 서비스 결과 값
        signUpService.requestSignUp(signUpBody)
            .enqueue(object : Callback<SignUpResponse> {
                val signUpDialog = SignUpDialog()

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    signUpDialog.connectionFail(context, sweetAlertDialog)
                }

                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    val intent = Intent(context, Intro::class.java)

                    Log.d("500씨발련아", "data: ${response.errorBody()?.string()}")

                    signUpDialog.connectionSuccess(
                        response.code(),
                        context,
                        response.errorBody()?.string().toString(),
                        intent,
                        sweetAlertDialog
                    )

                }

            })
    }
}