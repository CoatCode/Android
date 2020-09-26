package com.junhyuk.daedo.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.junhyuk.daedo.emailLogin.server.EmailLoginInterface
import com.junhyuk.daedo.workingNetwork.BaseUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DaedoApplication : Application(){
    private var loginInterface: EmailLoginInterface? = null
    lateinit var retrofit: Retrofit
    private var baseUrl : BaseUrl = BaseUrl()

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        val httpClient = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor(StethoInterceptor())
        val client = httpClient.build()

         retrofit = Retrofit.Builder()
            .baseUrl(baseUrl.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
             .client(client)
            .build()
        loginInterface = retrofit.create(EmailLoginInterface::class.java)
    }
    fun requestService(): EmailLoginInterface?{
        return loginInterface
    }
}