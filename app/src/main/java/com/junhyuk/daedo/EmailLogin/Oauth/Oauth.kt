package com.junhyuk.daedo.EmailLogin.Oauth

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginInterface
import com.junhyuk.daedo.WorkingNetwork.BaseUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//Oauth 2.0
class Oauth : AppCompatActivity() {

    companion object {


        fun getInstance(): EmailLoginInterface {
            var baseUrl : BaseUrl = BaseUrl()
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
            val response: Response? = null
            val interceptor = Interceptor() {
                //서버로 부터 받은 토큰들 변수에 저장
                val access_token = EmailLoginBody.instance?.access_token
                val refresh_token = EmailLoginBody.instance?.refresh_token



                val newRequest: Request

                Log.d("token","token"+response?.body())
                if (access_token != null && !access_token.equals("")) {


                    newRequest =
                        it.request().newBuilder().addHeader("Authorization", access_token)
                            .build()

                    if (response?.code() == 401) {
                        //401에러(토큰 만료 에러)가 뜰 때 RenewalToken에 refresh_token 함수를 호출하여 토큰 갱신
                        RenewalToken(refresh_token)
                    }
                } else newRequest = it.request()
                it.proceed(newRequest)

            }

            val builder = OkHttpClient.Builder()
            builder.interceptors().add(interceptor)
            builder.addNetworkInterceptor(StethoInterceptor())
            val client = builder.build()
            retrofitBuilder.client(client)


            val retrofit = retrofitBuilder.build()
            return retrofit.create(EmailLoginInterface::class.java)

        }



    }



}