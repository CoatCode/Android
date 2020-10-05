package com.junhyuk.daedo.feed.commentServer

import com.junhyuk.daedo.emailLogin.server.EmailLoginBody

data class CommentBody(val AccessToken : String = EmailLoginBody.instance!!.access_token)