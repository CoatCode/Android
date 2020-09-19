package com.junhyuk.daedo.emailLogin.oauth

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RenewalToken(var refresh_token: String?) : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        //함수 호출
        TokenRequest_Oauth(refresh_token)

    }
    private fun TokenRequest_Oauth(refresh_token: String?) {

        (application as DaedoApplication)
            .requestService()
            ?.GetRefreshToken(RefreshData(refresh_token))
            ?.enqueue(object : Callback<EmailLoginBody> {
                override fun onResponse(
                    call: Call<EmailLoginBody>,
                    response: Response<EmailLoginBody>
                ) {
                    //토큰 값을 정상적으로 받았을 시 EmailLoginBody에 토큰 값 저장
                    EmailLoginBody.instance = response.body()
                }

                override fun onFailure(call: Call<EmailLoginBody>, t: Throwable) {

                }


            })
    }

}