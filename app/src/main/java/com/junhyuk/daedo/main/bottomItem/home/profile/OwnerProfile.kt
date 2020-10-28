package com.junhyuk.daedo.main.bottomItem.home.profile

data class OwnerProfile(
    var id : String = "",
    var email : String =  "",
    var username : String = "",
    var profile : String = "",
    var followers : String = "",
    var following : String = ""
){
companion object{
    var instance : OwnerProfile? = null
}
}