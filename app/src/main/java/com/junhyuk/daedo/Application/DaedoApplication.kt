package com.junhyuk.daedo.Application

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginInterface
import com.junhyuk.daedo.WorkingNetwork.BaseUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DaedoApplication : Application(){
    private var LoginInteface: EmailLoginInterface? = null
    lateinit var retrofit: Retrofit
    var baseUrl : BaseUrl = BaseUrl()

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
        LoginInteface = retrofit.create(EmailLoginInterface::class.java)
    }
    fun requestService(): EmailLoginInterface?{
        return LoginInteface
    }
}