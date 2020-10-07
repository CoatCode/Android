package com.junhyuk.daedo.feed.commentServer

data class CommentBody(val content : String = ""){
    companion object {
        var instance: CommentBody? = null
    }
}