package com.junhyuk.daedo.EmailLogin.Server

data class EmailLoginBody(val email : String? = "Bearer",val password : String = ""
                          ,val access_token : String = "",val refresh_token : String = "") {
    companion object {
        var instance: EmailLoginBody? = null
    }
}