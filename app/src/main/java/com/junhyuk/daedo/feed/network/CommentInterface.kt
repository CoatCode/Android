package com.junhyuk.daedo.feed.network

import com.junhyuk.daedo.feed.getCommentList.GetCommentBody
import com.junhyuk.daedo.feed.writeComment.WriteCommentBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
//댓글 interface
interface CommentInterface{
    //댓글 작성
    @POST("/feed/post/1/comment")
    fun sendComment(
        //Header 로 토큰 전송
        @Header("Authorization") type:String,
        //Body 에 입력받은 댓글 전송
        @Body sendComment: WriteCommentBody

    ): Call<WriteCommentBody>
    @GET("/feed/post/:post_id/comments")
    fun getComment(
        @Header("Authorization") type: String
    ): Call<GetCommentBody>


}