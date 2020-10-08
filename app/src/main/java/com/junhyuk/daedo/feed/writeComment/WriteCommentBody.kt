package com.junhyuk.daedo.feed.writeComment
//댓글 작성 Body
data class WriteCommentBody(val text : String = ""){
    companion object {
        var instance: WriteCommentBody? = null
    }
}