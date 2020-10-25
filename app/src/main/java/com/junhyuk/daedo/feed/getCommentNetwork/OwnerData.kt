package com.junhyuk.daedo.feed.getCommentNetwork

data class OwnerData(
var id : String = "",
var email : String = "",
var username : String = "",
var profile : String = "",
var followers : String = "",
var following : String = "",
var button : String

){
    companion object{
        var instance : OwnerData? = null
    }
}
