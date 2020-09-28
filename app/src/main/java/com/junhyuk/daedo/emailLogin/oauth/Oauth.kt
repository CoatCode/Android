package com.junhyuk.daedo.emailLogin.oauth

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.emailLogin.server.EmailLoginInterface
import com.junhyuk.daedo.workingNetwork.baseUrl.BaseUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//Oauth 2.0
class Oauth {

    companion object {


        fun getInstance(): EmailLoginInterface {
            var baseUrl : BaseUrl = BaseUrl()
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
            val response: Response? = null
            val interceptor = Interceptor {
                //서버로 부터 받은 토큰들 변수에 저장
                val accessToken = EmailLoginBody.instance?.access_token
                val refreshToken = EmailLoginBody.instance?.refresh_token



                val newRequest: Request

                if (accessToken != null && !accessToken.equals("")) {


                    newRequest =
                        it.request().newBuilder().addHeader("Authorization",accessToken)
                            .build()

                    if (response?.code() == 401) {
                        //401에러(토큰 만료 에러)가 뜰 때 RenewalToken에 refresh_token 함수를 호출하여 토큰 갱신
                        RenewalToken(refreshToken)
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