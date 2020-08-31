package com.junhyuk.daedo.EmailLogin.Server

import com.google.gson.JsonObject
import com.junhyuk.daedo.EmailLogin.Oauth.RefreshData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EmailLoginInterface {
    //email로그인
    @POST("/auth/login")
    fun sendLoginInformation(
        @Body LoginInformation : EmailLoginBody


    ): Call<EmailLoginBody>
    //access토큰 재요청
    @POST("/auth/renewalToken")
    fun GetRefreshToken(
        @Body GetTokens : RefreshData

    ): Call<EmailLoginBody>

    @GET("/auth/user")
    fun GetUserInformation(
        @Query("authorization") AccessToken: String
    ): Call<JsonObject>
}