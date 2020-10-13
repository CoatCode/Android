package com.junhyuk.daedo.feed.getCommentList

data class SchoolData(
    var student : ArrayList<student>
){
    companion object{
        var instance : SchoolData? = null
    }
}