package com.junhyuk.daedo.main.bottomItem.home.workinRetrofit

import android.app.Application
import android.content.Context
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.main.bottomItem.home.data.FeedData
import com.junhyuk.daedo.main.bottomItem.home.server.FeedResponse
import com.junhyuk.daedo.main.bottomItem.home.server.FeedService
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SetUpRetrofit() {

    internal fun setUpRetrofit(
        getApplication: Application,
        page: Int,
        context: Context
    ): ArrayList<FeedData> {

        val feedService =
            (getApplication as DaedoApplication).retrofit.create(FeedService::class.java)

        val feedList = ArrayList<FeedData>()
        var feedData: FeedData

        feedService.requestFeed(page).enqueue(object : Callback<FeedResponse> {

            override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>) {
                val jsonArray = JSONArray(response.message().toString())
            }

            override fun onFailure(call: Call<FeedResponse>, t: Throwable) {

            }
        })

        return feedList
    }

}