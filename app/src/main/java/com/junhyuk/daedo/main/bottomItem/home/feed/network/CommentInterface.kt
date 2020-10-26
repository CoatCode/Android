package com.junhyuk.daedo.main.bottomItem.home.feed.network

import com.junhyuk.daedo.main.bottomItem.home.feed.getCommentNetwork.CommentData
import com.junhyuk.daedo.main.bottomItem.home.feed.writeCommentNetwork.WriteCommentBody
import retrofit2.Call
import retrofit2.http.*

//댓글 interface
interface CommentInterface{
    //작성한 댓글을 전송한다
    @POST("/feed/post/3/comment")
    fun sendComment(
        //Header 로 토큰 전송
        @Header("Authorization") type:String,
        //Body 에 입력받은 댓글 전송
        @Body sendComment: WriteCommentBody
    ): Call<WriteCommentBody>

    //댓글 목록을 받아온다
    @GET("/feed/post/3/comments")
    fun getComment(
        @Header("Authorization") type: String
    ): Call<ArrayList<CommentData>>

    //작성한 댓글을 삭제 한다
    @DELETE("/feed/post/3/comment/{comment-id}")
    fun deleteComment(
        //@Path("post-id") post : String,
        @Path("comment-id") comment : String,
        @Header("Authorization") type:String
    ): Call<Void>
}