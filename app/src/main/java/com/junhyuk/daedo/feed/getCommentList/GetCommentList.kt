package com.junhyuk.daedo.feed.getCommentList

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.feed.network.CommentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCommentList : AppCompatActivity(){
    val token : String = com.junhyuk.daedo.emailLogin.server.EmailLoginBody.instance!!.access_token
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        request()
    }
    private fun request() {

        (application as DaedoApplication).retrofit.create(CommentInterface::class.java)
            .getComment("Bearer $token")
            .enqueue(object : Callback<GetCommentBody> {
                override fun onResponse(call: Call<GetCommentBody>,
                                        response: Response<GetCommentBody>

                ) {

                    if (response.code() == 200){
                        Log.d("Email","data: ${response.body()}")
                        Toast.makeText(applicationContext,"로그인 성공", Toast.LENGTH_LONG).show()
                        

                    }
                    else if (response.code() == 401){
                        Log.d("Email", "data: ${response.errorBody()?.string().toString()}")
                        Toast.makeText(applicationContext,"로그인 실패", Toast.LENGTH_LONG).show()
                    }

                }


                override fun onFailure(call: Call<GetCommentBody>, t: Throwable) {
                    Log.d("failure","falid")
                    Toast.makeText(applicationContext,"서버 연결 실패", Toast.LENGTH_LONG).show()
                }


            })
    }

}