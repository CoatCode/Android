package com.junhyuk.daedo.feed.commentServer

import com.junhyuk.daedo.emailLogin.server.EmailLoginBody

data class CommentBody(val content : String = "",val token : String = EmailLoginBody.instance!!.access_token){
    companion object {
        var instance: CommentBody? = null
    }
}