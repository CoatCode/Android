package com.junhyuk.daedo.feed.getCommentList

data class SchoolData(
    var comment_id: String = "",
    var content: String = "",
    var created_at: String = "",
    var owner: ownerData?
){
    companion object{
        var instance : SchoolData? = null
    }
}