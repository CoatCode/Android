package com.junhyuk.daedo.main.bottomItem.profile

data class UserProfileData(
    var id: String = "",
    var owner: OwnerProfileData,
    var title: String = "",
    var content: String = "",
    var image_urls : ArrayList<String>,
    var liked_people : ArrayList<Int>,
    var like_count: Int = 0,
    var comment_count: Int = 0,
    var view_count: Int = 0,
    var tag : ArrayList<String>,
    var created_at: String = ""

) {
    companion object{
        var instance : UserProfileData? = null
    }
}