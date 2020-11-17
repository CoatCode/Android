package com.junhyuk.daedo.dataBase.userDataHandler

data class UserInformation (var id : Int = 0, var email: String? = "",var username : String? = "",
                            var profile : String? = "", var description : String? = "" , var followers : Int = 0, var following : Int = 0){
    companion object{
        var instance: UserInformation? = null
    }
}