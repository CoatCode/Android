package com.junhyuk.daedo.main.bottomItem.home.workinRetrofit

import android.app.Application
import android.content.Context
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.main.bottomItem.home.server.FeedService

class SetUpRetrofit {

    internal fun setUpRetrofit(
        getApplication: Application,
        context: Context
    ){

        val feedService =
            (getApplication as DaedoApplication).retrofit.create(FeedService::class.java)




    }

}