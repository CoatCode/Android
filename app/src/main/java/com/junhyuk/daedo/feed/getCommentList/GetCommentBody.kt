package com.junhyuk.daedo.feed.getCommentList
 data class GetCommentBody(
//     var comment_id : String = "",
//     var owner : String = "",
//     var id : String = "",
//     var email : String = "",
       var username : String = ""
//     var profile : String = "",
//     var followers : String = "",
//     var foolowing : String = "",
//     var content : String = "",
//     var creater_at : String = ""


 )
 {
     companion object {
         var instance: GetCommentBody? = null
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
////"content": "",
////"created_at": ""