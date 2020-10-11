package com.junhyuk.daedo.feed.getCommentList

import com.google.gson.annotations.SerializedName

data class GetCommentBody(
     @SerializedName("comment_id")
     var comment_id : String = "",
     @SerializedName("owner")
     var owner : String = "",
     @SerializedName("id")
     var id : String = "",
     @SerializedName("email")
     var email : String = "",
     @SerializedName("username")
     var username : String = "",
     @SerializedName("profile")
     var profile : String = "",
     @SerializedName("followers")
     var followers : String = "",
     @SerializedName("following")
     var following : String = "",
     @SerializedName("content")
     var content : String = "",
     @SerializedName("created_at")
     var created_at : String = ""



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