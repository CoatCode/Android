package com.junhyuk.daedo.main.bottomItem.profile


data class UserProfileData(
    var id: Int,
    var owner: OwnerProfileData,
    var title: String,
    var content: String,
    var image_urls: ArrayList<String>,
    var liked_people: ArrayList<String>,
    var like_count: Int,
    var view_count: Int,
    var comment_count: Int,
    var tag: ArrayList<String>,
    var created_at: String
) {
    companion object{
        var instance : ArrayList<UserProfileData> = arrayListOf()
    }
}