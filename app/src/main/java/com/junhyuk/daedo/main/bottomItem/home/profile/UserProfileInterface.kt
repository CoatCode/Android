package com.junhyuk.daedo.main.bottomItem.home.profile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserProfileInterface {
    @GET("/user/{user-id}/posts")
    fun getComment(
        @Path("user-id") post : String
    ): Call<ArrayList<UserProfileData>>
}