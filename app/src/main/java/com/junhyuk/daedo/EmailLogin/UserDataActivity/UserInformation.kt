package com.junhyuk.daedo.EmailLogin.UserDataActivity

data class UserInformation (var email: String = "",var username : String = "", var profile : String = ""){
    companion object{
        var instance: UserInformation? = null
    }
}