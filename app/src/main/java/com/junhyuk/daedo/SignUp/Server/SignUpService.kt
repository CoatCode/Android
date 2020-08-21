package com.junhyuk.daedo.SignUp.Server

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("/auth/signUp")
    fun requestSignUp(
        //INPUT 정의
        @Body signUpBody: SignUpBody
    ) : Call<SignUpResponse> //OUTPUT 정의

}