package com.junhyuk.daedo.feed.getCommentList

data class SchoolData(
    var comment_id : String = "",
    var id : List<ownerData>,
    var content : String = "",
    var created_at : String = ""
){
    companion object{
        var instance : SchoolData? = null
    }
}