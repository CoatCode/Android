package com.junhyuk.daedo.main.bottomItem.comment.network

import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import com.junhyuk.daedo.main.bottomItem.comment.writeCommentNetwork.WriteCommentBody
import retrofit2.Call
import retrofit2.http.*

//댓글 interface
interface CommentInterface{
    //작성한 댓글을 전송한다
    @POST("/feed/post/{post-id}/comment")
    fun sendComment(
        @Path("post-id") post : Int,
        //Header 로 토큰 전송
        @Header("Authorization") type:String,
        //Body 에 입력받은 댓글 전송
        @Body sendComment: WriteCommentBody
    ): Call<WriteCommentBody>

    //댓글 목록을 받아온다
    @GET("/feed/post/{post-id}/comments")
    fun getComment(
        @Path("post-id") post : Int,
        @Header("Authorization") type: String
    ): Call<ArrayList<CommentData>>

    //작성한 댓글을 삭제 한다
    @DELETE("/feed/post/{post-id}/comment/{comment-id}")
    fun deleteComment(
        @Path("post-id") post : Int,
        @Path("comment-id") comment : String,
        @Header("Authorization") type:String
    ): Call<Void>
}