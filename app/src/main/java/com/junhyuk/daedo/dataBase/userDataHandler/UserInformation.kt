package com.junhyuk.daedo.dataBase.userDataHandler

data class UserInformation (var id : String = "", var email: String = "",var username : String = "",
                            var profile : String = "", var followers : String = "", var following : String = ""){
    companion object{
        var instance: UserInformation? = null
    }
}