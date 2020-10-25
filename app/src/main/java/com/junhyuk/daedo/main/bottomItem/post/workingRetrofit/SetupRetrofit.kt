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
import com.junhyuk.daedo.main.bottomItem.post.server.PostDialog
import com.junhyuk.daedo.main.bottomItem.post.server.PostResponse
import com.junhyuk.daedo.main.bottomItem.post.server.PostService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SetupRetrofit {
    //네트워크 작업
    internal fun setUpRetrofit(
        getApplication: Application,
        context: Context,
        postTitle: String,
        postContent: String,
        hashTag: String,
        requestBody: ArrayList<RequestBody>,
        fileName: ArrayList<String>
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

        //서버에 보낼 데이터
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        builder.addFormDataPart("title", postTitle)
        builder.addFormDataPart("content", postContent)
        if (hashTag.isNotBlank() || hashTag.isNotEmpty()){
            builder.addFormDataPart("tag", hashTag)
        }

        var index = 1
        requestBody.forEach {
            Log.d("indexData", "name: ${fileName[index-1]}")
            Log.d("indexData", "name: ${it.contentType()}")
            Log.d("indexData", "file: ${it.contentType()}")
            builder.addFormDataPart("image$index", fileName[index-1], it)
            Log.d("indexData", "name: image$index")
            index++
        }

        val postBody: RequestBody = builder.build()

        CoroutineScope(Dispatchers.IO).launch {
            //retrofit
            postService.requestPost("Bearer $token", postBody)
                .enqueue(object : Callback<PostResponse> {
                    val postDialog = PostDialog()

                    //통신 실패
                    override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                        postDialog.connectionFail(context, sweetAlertDialog)
                        Log.d("throw", "data: ${t.printStackTrace()}")
                    }

                    //통신 성공
                    override fun onResponse(
                        call: Call<PostResponse>,
                        response: Response<PostResponse>
                    ) {
                        val intent = Intent(context, MainActivity::class.java)

                        Log.d("code", "res: ${response.message()}")

                        postDialog.connectionSuccess(
                            response.code(),
                            response.message(),
                            context,
                            response,
                            intent,
                            sweetAlertDialog
                        )
                    }
                })
        }

    }
}