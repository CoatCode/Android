package com.junhyuk.daedo.main.bottomItem.post.server

import com.junhyuk.daedo.main.bottomItem.post.adapter.PostBody
import com.junhyuk.daedo.main.bottomItem.post.adapter.PostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {

    @POST("/feed/post/post")
    fun requestSignUp(

        @Body postBody: PostBody //INPUT 정의


    ) : Call<PostResponse> //OUTPUT 정의

}