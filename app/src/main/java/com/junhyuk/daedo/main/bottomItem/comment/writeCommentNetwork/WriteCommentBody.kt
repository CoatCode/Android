package com.junhyuk.daedo.main.bottomItem.comment.writeCommentNetwork
//댓글 작성 Body
data class WriteCommentBody(val content : String = ""){
    companion object {
        var instance: WriteCommentBody? = null
    }
}