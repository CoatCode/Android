package com.junhyuk.daedo.feed.commentServer

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Comment{
    @POST("/feed/post/1/comment")
    fun sendComment(
        @Header("Authorization") type:String,
        @Body sendComment: String

    ): Call<CommentBody>

}