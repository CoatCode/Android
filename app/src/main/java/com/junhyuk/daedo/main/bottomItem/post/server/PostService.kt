package com.junhyuk.daedo.main.bottomItem.post.server

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface PostService {

    //업로드 부분
    @POST("/feed/post")
    fun requestPost(
        @Header("Authorization") type: String,
        @Body postBody: RequestBody
    ): Call<PostResponse>

}