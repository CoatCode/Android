package com.junhyuk.daedo.main.bottomItem.profile

import com.junhyuk.daedo.main.bottomItem.follow.FollowResponse
import com.junhyuk.daedo.main.bottomItem.home.data.LikeResponse
import retrofit2.Call
import retrofit2.http.*

interface UserProfileInterface {
    @GET("/user/{user-id}/posts")
    fun getUserProfile(
        @Path("user-id") id : Int
    ): Call<ArrayList<UserProfileData>>
    //팔로우
    @POST("feed/post/{user-id}/follow")
    fun requestFollow(
        @Header("Authorization") type: String,
        @Path("user-id") id: Int
    ): Call<FollowResponse>

    //좋아요 취소
    @DELETE("/feed/post/{user-id}/like")
    fun requestFollowCancel(
        @Header("Authorization") type: String,
        @Path("user-id") id: Int
    ): Call<FollowResponse>

    //좋아요 여부
    @GET("/feed/post/{user-id}/follow")
    fun requestFollowBool(
        @Header("Authorization") type: String,
        @Path("user-id") id: Int
    ): Call<FollowResponse>
}