package com.junhyuk.daedo.EmailLogin.UserDatabase

data class UserInformation(val email : String, val username: String, val profile : String){

    companion object{
        var instance : UserInformation? = null
    }
}

