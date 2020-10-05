package com.junhyuk.daedo.main.bottomItem.post.workingRetrofit

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.main.activity.MainActivity
import com.junhyuk.daedo.main.bottomItem.post.server.PostBody
import com.junhyuk.daedo.main.bottomItem.post.server.PostDialog
import com.junhyuk.daedo.main.bottomItem.post.server.PostResponse
import com.junhyuk.daedo.main.bottomItem.post.server.PostService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class SetupRetrofit {
    //네트워크 작업
    internal fun setUpRetrofit(
        getApplication: Application,
        context: Context,
        images: String,
        postTitle: String,
        postContent: String,
        hashTag: String
    ) {

        //로딩 다이얼로그
        val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
        sweetAlertDialog
            .setTitleText("로딩 중")
            .setCancelable(false)
        sweetAlertDialog.show()

        val postService =
            (getApplication as DaedoApplication).retrofit.create(PostService::class.java)

        val token: String? = EmailLoginBody.instance?.access_token

        val file = File(images)

        //서버에 보낼 데이터
        val imageRequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val image = MultipartBody.Part.createFormData("image", images, imageRequestBody)

        Log.d("image", "image: $image")

        //retrofit
        postService.requestPost("Bearer $token", PostBody(image, postTitle, postContent, hashTag))
            .enqueue(object : Callback<PostResponse> {
                val postDialog = PostDialog()

                //통신 실패
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.d("response", "call: ${call.execute().code()}")
                    postDialog.connectionFail(context, sweetAlertDialog)
                }

                //통신 성공
                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    val intent = Intent(context, MainActivity::class.java)

                    postDialog.connectionSuccess(
                        response.code(),
                        response.message(),
                        context,
                        response.body().toString(),
                        intent,
                        sweetAlertDialog
                    )
                }
            })

    }
}