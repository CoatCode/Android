package com.junhyuk.daedo.main.bottomItem.post.workingRetrofit

import android.app.Application
import android.content.Context
import android.net.Uri
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.main.bottomItem.post.server.PostService

class SetupRetrofit {
    //네트워크 작업
    internal fun setUpRetrofit(
        getApplication: Application,
        context: Context,
        imageList: ArrayList<Uri>,
        postContent: String,
        postTitle: String,
        hashTag: ArrayList<String>
    ){
        val postService =
            (getApplication as DaedoApplication)
                .retrofit
                .create(PostService::class.java)

        val token : String = EmailLoginBody.instance!!.access_token
//
//        //retrofit
//        postService
//            .requestPost("Bearer $token", )
    }
}