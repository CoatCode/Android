package com.junhyuk.daedo.main.bottomItem.home.profile

data class OwnerProfileData(
    var id : String = "",
    var email : String =  "",
    var username : String = "",
    var profile : String = "",
    var followers : String = "",
    var following : String = ""
){
companion object{
    var instance : OwnerProfileData? = null
}
}