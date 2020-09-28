package com.junhyuk.daedo.main.bottomItem.post.server

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface PostService {

    //업로드 부분
    @Multipart
    @POST("/feed/post/post")
    fun requestPost(
        @Header("Authorization") type: String,
        @Part imageFile: ArrayList<MultipartBody.Part>,
        @Body postBody: PostBody
    ): Call<PostResponse>

}