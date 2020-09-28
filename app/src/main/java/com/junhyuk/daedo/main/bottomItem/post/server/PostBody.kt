package com.junhyuk.daedo.main.bottomItem.post.server

data class PostBody(
    val title: String,
    val text: String,
    val tag: ArrayList<String>
)