package com.junhyuk.daedo.feed.getCommentList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetCommentBody(
    @SerializedName("comment_id")
    @Expose
    private var commentId : String,
    @SerializedName("owner")
    @Expose
    private var owner : String,
    @SerializedName("content")
    @Expose
    private var content : String,
    @SerializedName("created_at")
    @Expose
    private var createdAt : String

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