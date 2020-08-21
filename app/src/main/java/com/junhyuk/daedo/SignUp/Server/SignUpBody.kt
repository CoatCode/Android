package com.junhyuk.daedo.SignUp.Server

data class SignUpBody(
    val email: String,
    val password: String,
    val username: String,
    val profile: String
)