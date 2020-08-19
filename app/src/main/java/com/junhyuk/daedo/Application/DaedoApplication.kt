package com.junhyuk.daedo.Application

import android.app.Application
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginInterface
import com.junhyuk.daedo.WorkingNetwork.BaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DaedoApplication : Application(){
    private var LoginInteface: EmailLoginInterface? = null
    lateinit var retrofit: Retrofit
    var baseUrl : BaseUrl = BaseUrl()

    override fun onCreate() {
        super.onCreate()

         retrofit = Retrofit.Builder()
            .baseUrl(baseUrl.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        LoginInteface = retrofit.create(EmailLoginInterface::class.java)
    }
    fun requestService(): EmailLoginInterface?{
        return LoginInteface
    }
}