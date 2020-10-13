package com.junhyuk.daedo.feed.getCommentList

data class ownerData(
var id : String = "",
var email : String = "",
var username : String = "",
var profile : String = "",
var followers : String = "",
var following : String = ""
){
    companion object{
        var instance : ownerData? = null
    }
}

//"comment_id": 0,
//"owner": {
//    "id": 0,
//    "email": "",
//    "username": "",
//    "profile": "",
//    "followers": 0,
//    "following": 0
//},
//"content": "",
//"created_at": ""