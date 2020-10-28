package com.junhyuk.daedo.main.bottomItem.home.profile

data class UserProfile(
    var id: String = "",
    var owner: OwnerProfile,
    var title: String = "",
    var content: String = "",
//"image_urls": [
//"image_url1",
//"image_url2",
//"image_url3", ...
//],
//"liked_people": [
//1,
//2,
//3, ...
//],
    var like_count: String = "",
    var comment_count: String = "",
    var view_count: String = "",
//"tag" : [
//"태그1",
//"태그2",
//"태그3", ...
//],
    var created_at: String = ""


) {
    companion object{
        var instance : UserProfile? = null
    }
}