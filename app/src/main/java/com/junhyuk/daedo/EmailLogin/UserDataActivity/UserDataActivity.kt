package com.junhyuk.daedo.EmailLogin.UserDataActivity

import android.app.Application
import android.util.Log
import com.google.gson.JsonObject
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//유저 정보를 받아오기 위해 서버와 통신하는 클래스
class UserDataActivity {
  internal fun GetUserData(
      getApplication: Application
  ) {
      val token : String = EmailLoginBody.instance!!.access_token
        Log.d("token", "token=$token")
        (getApplication as DaedoApplication)
        .requestService()
        ?.GetUserInformation("Bearer $token",null)
        ?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("stoken","token="+EmailLoginBody.instance?.access_token)
                Log.d("stoken","stoken$token")
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("ftoken","token="+EmailLoginBody.instance?.access_token)
            }

        })

  }
}




