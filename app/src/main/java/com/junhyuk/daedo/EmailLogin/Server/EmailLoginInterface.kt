package com.junhyuk.daedo.EmailLogin.Server

import com.junhyuk.daedo.EmailLogin.Oauth.RefreshData
import com.junhyuk.daedo.EmailLogin.UserDataHandle.UserInformation
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

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

    //User 정보 받아오기
    @GET("/user")
    fun GetUserInformation(
        @Header("Authorization") type:String
    ): Call<UserInformation>
}