package com.junhyuk.daedo.feed.comment

import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface Comment{
    @POST("/feed/post/:1/comment")
    fun sendComment(
        @Header("Authorization") type:String
       //@Body sendComment : EmailLoginBody

    ): Call<EmailLoginBody>
}