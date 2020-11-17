package com.junhyuk.daedo.main.bottomItem.profile

data class OwnerProfileData(
    var id : String = "",
    var email : String =  "",
    var username : String? = "",
    var profile : String = "",
    var followers : Int = 0,
    var following : Int = 0
){
companion object{
    var instance : OwnerProfileData? = null
}
}