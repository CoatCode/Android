package com.junhyuk.daedo.main.bottomItem.profile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserProfileInterface {
    @GET("/user/{user-id}/posts")
    fun getUserProfile(
        @Path("user-id") post : Int
    ): Call<ArrayList<UserProfileData>>
}