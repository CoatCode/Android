package com.junhyuk.daedo.EmailLogin.UserDataActivity

import android.app.Application
import android.util.Log
import com.google.gson.JsonObject
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataActivity {
  internal fun GetUserData(
      getApplication: Application
  ) {
      val token : String = EmailLoginBody.instance!!.access_token

    (getApplication as DaedoApplication)
        .requestService()
        ?.GetUserInformation(EmailLoginBody(token))
        ?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("token","token"+EmailLoginBody.instance?.access_token)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("token","token"+EmailLoginBody.instance?.access_token)
            }

        })

  }
}




