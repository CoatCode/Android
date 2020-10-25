package com.junhyuk.daedo.main.bottomItem.home.server

import com.junhyuk.daedo.main.bottomItem.home.data.FeedData
import com.junhyuk.daedo.main.bottomItem.home.data.LikeResponse
import retrofit2.Call
import retrofit2.http.*

interface FeedService {

    //피드 부분
    @GET("/feed/post/all")
    fun requestFeed(
        @Query ("page") page: Int
    ): Call<List<FeedData>>

    //좋아요
    @POST("feed/post/{post-id}/like")
    fun requestLike(
        @Header("Authorization") type: String,
        @Path("post-id") id: Int
    ): Call<LikeResponse>

    //좋아요 취소
    @DELETE("/feed/post/{post-id}/like")
    fun requestLikeCancel(
        @Header("Authorization") type: String,
        @Path("post-id") id: Int
    ): Call<LikeResponse>

    //좋아요 여부
    @GET("/feed/post/{post-id}/like")
    fun requestLikeBool(
        @Header("Authorization") type: String,
        @Path("post-id") id: Int
    ): Call<LikeResponse>

}