package com.junhyuk.daedo.main.bottomItem.profile

data class OwnerProfileData(
    var id : Int = 0,
    var email : String =  "",
    var username : String = "",
    var profile : String = "",
    var description : String = "",
    var following : Int = 0,
    var followers : Int = 0
){
companion object{
    var instance : OwnerProfileData? = null
}
}