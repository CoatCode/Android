package com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork

data class CommentData(
    var comment_id: String = "",
    var content: String = "",
    var created_at: String = "",
    var button : String = "",
    var owner: OwnerData?
){
    companion object{
        var instance : CommentData? = null
    }
}