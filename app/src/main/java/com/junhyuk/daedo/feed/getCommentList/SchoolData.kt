package com.junhyuk.daedo.feed.getCommentList

data class SchoolData(
    var test : ArrayList<String?>
){
    companion object{
        var instance : SchoolData? = null
    }
}