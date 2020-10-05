package com.junhyuk.daedo.main.bottomItem.post.server

import okhttp3.MultipartBody

data class PostBody(
    val image: MultipartBody.Part,
    val title: String,
    val text: String,
    val tag: String
)