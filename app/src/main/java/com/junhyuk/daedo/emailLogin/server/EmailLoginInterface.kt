package com.junhyuk.daedo.emailLogin.server

import com.junhyuk.daedo.dataBase.userDataHandler.UserInformation
import com.junhyuk.daedo.emailLogin.oauth.RefreshData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface EmailLoginInterface {
    //email 로그인
    @POST("/auth/login")
    fun sendLoginInformation(
        @Body LoginInformation : EmailLoginBody
    ): Call<EmailLoginBody>

    //access 토큰 재요청
    @POST("/auth/renewalToken")
    fun getRefreshToken(
        @Body GetTokens : RefreshData

    ): Call<EmailLoginBody>

    //User 정보 받아오기
    @GET("/user")
    fun getUserInformation(
        //header 로 토큰 전송
        @Header("Authorization") type:String
    ): Call<UserInformation>

}