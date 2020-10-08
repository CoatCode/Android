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
        images: ArrayList<String>,
        imageName: String,
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

        //서버에 보낼 데이터
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        /*val titleBody: RequestBody
        val contentBody: RequestBody
        val tagBody: RequestBody
        val filePart: MultipartBody.Part
        var requestBody: RequestBody
        val mapRequestBody = LinkedHashMap<String, RequestBody>()
        val imageBody: List<MultipartBody.Part> = ArrayList()*/

        if (hashTag == "") {

            builder.addFormDataPart("title", postTitle)
            builder.addFormDataPart("text", postContent)

            //titleBody = RequestBody.create(MediaType.parse("text/plain"), postTitle)
            //contentBody = RequestBody.create(MediaType.parse("text/plain"), postContent)


            images.forEach {
                val file = File(it)

                Log.d("image", "data: ${file.name}")
                Log.d("image", "file: ${file.exists()}")
                Log.d("image", "name: $imageName")

                builder.addFormDataPart("image", file.name, RequestBody.create(MediaType.parse("multipart/form-data"), file))
            }



        } else {

            builder.addFormDataPart("title", postTitle)
            builder.addFormDataPart("text", postContent)
            builder.addFormDataPart("tag", hashTag)

            //titleBody = RequestBody.create(MediaType.parse("text/plain"), postTitle)
            //contentBody = RequestBody.create(MediaType.parse("text/plain"), postContent)
            //tagBody = RequestBody.create(MediaType.parse("text/plain"), hashTag)

            images.forEach {
                val file = File(it)

                Log.d("image", "data: ${file.name}")
                Log.d("image", "file: ${file.exists()}")
                Log.d("image", "name: $imageName")

                builder.addFormDataPart("image", file.name, RequestBody.create(MediaType.parse("multipart/form-data"), file))
            }

        }

        val requestBody: RequestBody = builder.build()

        CoroutineScope(Dispatchers.IO).launch {
            //retrofit
            postService.requestPost("Bearer $token", requestBody)
                .enqueue(object : Callback<PostResponse> {
                    val postDialog = PostDialog()

                    //통신 실패
                    override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                        postDialog.connectionFail(context, sweetAlertDialog)
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
                            response.body().toString(),
                            intent,
                            sweetAlertDialog
                        )
                    }
                })
        }

    }
}