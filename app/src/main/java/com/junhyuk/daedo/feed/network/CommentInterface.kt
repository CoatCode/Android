package com.junhyuk.daedo.feed.network

import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import com.junhyuk.daedo.feed.writeCommentNetwork.WriteCommentBody
import retrofit2.Call
import retrofit2.http.*

//댓글 interface
interface CommentInterface{

    @POST("/feed/post/12/comment")
    fun sendComment(
        //Header 로 토큰 전송
        @Header("Authorization") type:String,
        //Body 에 입력받은 댓글 전송
        @Body sendComment: WriteCommentBody

    ): Call<WriteCommentBody>

    @GET("/feed/post/12/comments")
    fun getComment(
        @Header("Authorization") type: String
    ): Call<ArrayList<CommentData>>
    @DELETE("/feed/post/12/comment/{comment-id}")
    fun deleteComment(
        //@Path("post-id") post : String,
        @Path("comment-id") comment : String,
        @Header("Authorization") type:String
    )
}