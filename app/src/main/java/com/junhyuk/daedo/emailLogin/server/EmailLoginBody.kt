package com.junhyuk.daedo.emailLogin.server

data class EmailLoginBody(val email : String? = "",val password : String = ""
                          ,val access_token : String = "",val refresh_token : String = "") {
    companion object {
        var instance: EmailLoginBody? = null
    }
}