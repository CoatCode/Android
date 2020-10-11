package com.junhyuk.daedo.feed.getCommentList

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("id")
    @Expose
     val id: Int? = null,
    @SerializedName("email")
    @Expose
     val email: String? = null,

    @SerializedName("username")
    @Expose
     val username: String? = null,

    @SerializedName("profile")
    @Expose
     val profile: String? = null,

    @SerializedName("followers")
    @Expose
     val followers: Int? = null,

    @SerializedName("following")
    @Expose
     val following: Int? = null
)
{
    companion object {
        var instance: Owner? = null
    }
}