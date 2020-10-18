package com.junhyuk.daedo.main.bottomItem.home.server

import com.junhyuk.daedo.main.bottomItem.post.server.PostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {

    //피드 부분
    @GET("/feed/post/all")
    fun requestPost(
        @Query ("page") page: Int
    ): Call<PostResponse>

}