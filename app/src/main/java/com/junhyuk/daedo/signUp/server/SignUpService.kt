package com.junhyuk.daedo.signUp.server

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("/auth/sign-up")
    fun requestSignUp(
        //INPUT 정의
        @Body signUpBody: RequestBody
    ) : Call<SignUpResponse> //OUTPUT 정의

}