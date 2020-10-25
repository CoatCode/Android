package com.junhyuk.daedo.main.bottomItem.home.workinRetrofit

import com.junhyuk.daedo.main.bottomItem.home.server.FeedService
import com.junhyuk.daedo.workingNetwork.baseUrl.BaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {

    private var instance: RetrofitClient? = null
    private val retrofit: Retrofit
    private val baseUrl: BaseUrl = BaseUrl()

    init {
        this.retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Synchronized
    fun getInstance(): RetrofitClient?{
        if(instance == null){
            instance = RetrofitClient()
        }
        return instance
    }

    internal fun getApi(): FeedService{
        return retrofit.create(FeedService::class.java)
    }
}