package com.junhyuk.daedo.main.bottomItem.home.data

import com.junhyuk.daedo.main.bottomItem.profile.OwnerProfileData

data class FeedCommentData(
    val comment_id: Int,
    val owner: OwnerProfileData,
    val content: String,
    val created_at: String
)