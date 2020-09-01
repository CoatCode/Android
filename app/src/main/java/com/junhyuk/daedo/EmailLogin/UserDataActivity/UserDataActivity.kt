package com.junhyuk.daedo.EmailLogin.UserDataActivity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.d("aaa","aaa")
        userdata()
    }
private fun userdata(){
    (application as DaedoApplication)
        .requestService()
        ?.GetUserInformation(EmailLoginBody.instance!!.access_token)
        ?.enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("token","token"+EmailLoginBody.instance?.access_token)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("token","token"+EmailLoginBody.instance?.access_token)
            }

        })
}
}




