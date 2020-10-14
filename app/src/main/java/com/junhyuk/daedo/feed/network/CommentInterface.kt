package com.junhyuk.daedo.feed.network

import com.junhyuk.daedo.feed.getCommentList.SchoolData
import com.junhyuk.daedo.feed.writeComment.WriteCommentBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

//댓글 interface
interface CommentInterface{

    @POST("/feed/post/1/comment")
    fun sendComment(
        //Header 로 토큰 전송
        @Header("Authorization") type:String,
        //Body 에 입력받은 댓글 전송
        @Body sendComment: WriteCommentBody

    ): Call<WriteCommentBody>

    @GET("/feed/post/1/comments")
    fun getComment(
        @Header("Authorization") type: String
    ): Call<ArrayList<SchoolData>>


}