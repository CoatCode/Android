package com.junhyuk.daedo.emailLogin.oauth

data class RefreshData(

    var refresh_token : String?
) {
    companion object {
        var instance: RefreshData? = null
    }
}