package com.junhyuk.daedo.main.bottomItem.home.profile

data class UserProfileData(
    var id: String = "",
    var owner: OwnerProfileData,
    var title: String = "",
    var content: String = "",
    var image_urls : ArrayList<String>,
    var liked_people : ArrayList<Int>,
    var like_count: String = "",
    var comment_count: String = "",
    var view_count: String = "",
    var tag : ArrayList<String>,
    var created_at: String = ""

) {
    companion object{
        var instance : UserProfileData? = null
    }
}