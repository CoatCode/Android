package com.junhyuk.daedo.SignUp.WorkingRetrofit

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.Main.MainActivity
import com.junhyuk.daedo.SignUp.Server.SignUpBody
import com.junhyuk.daedo.SignUp.Server.SignUpDialog
import com.junhyuk.daedo.SignUp.Server.SignUpResponse
import com.junhyuk.daedo.SignUp.Server.SignUpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SetupRetrofit {
    //네트워크 작업
    internal fun setupRetrofit(email: String, password: String, userName: String, base64: String, getApplication: Application, context: Context) {

        val signUpService =
            (getApplication as DaedoApplication).retrofit.create(SignUpService::class.java)

        signUpService.requestSignUp(SignUpBody(email, password, userName, base64))
            .enqueue(object : Callback<SignUpResponse> {
                val signUpDialog = SignUpDialog()

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    signUpDialog.connectionFail(context)
                }

                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {

                    //통신 성공 응답값을 받아옴
                    Log.d("data2", "data: ${response.code()}")

                    val intent = Intent(context, MainActivity::class.java)

                    signUpDialog.connectionSuccess(
                        response.code(),
                        response.message(),
                        context,
                        response.errorBody()?.string().toString(),
                        intent
                    )

                }
            })
    }
}