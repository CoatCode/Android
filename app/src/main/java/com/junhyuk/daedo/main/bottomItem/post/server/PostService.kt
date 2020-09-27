package com.junhyuk.daedo.main.bottomItem.post.server

import com.junhyuk.daedo.main.bottomItem.post.adapter.PostBody
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface PostService {

    //업로드 부분
    @Multipart
    @POST("/feed/post/post")
    fun requestPost(
        @Header("Authorization") type: String,
        @Part image: MultipartBody.Part?,
        @Part("upload") name: RequestBody?,
        @Body postBody: PostBody
    ): Call<ResponseBody?>?

}