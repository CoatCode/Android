package com.junhyuk.daedo.signUp.server

data class SignUpBody(
    val email: String,
    val password: String,
    val username: String,
    val profile: String
)