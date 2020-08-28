package com.junhyuk.daedo.EmailLogin.Oauth

data class RefreshData(

    var refresh_token : String?
) {
    companion object {
        var instance: RefreshData? = null
    }
}