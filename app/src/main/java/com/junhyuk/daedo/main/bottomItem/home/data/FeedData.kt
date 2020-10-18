package com.junhyuk.daedo.main.bottomItem.home.data

data class FeedData (
    val owner: FeedOwnerData,
    val title: String,
    val comment: String,
    val imageArrayList: ArrayList<String>,
    val likePeople: ArrayList<String>,
    val likeCount: Int,
    val viewCount: Int,
    val tagArrayList: ArrayList<String>,
    val createdAt: String
)