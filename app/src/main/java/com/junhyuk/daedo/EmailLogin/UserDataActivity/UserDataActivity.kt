package com.junhyuk.daedo.EmailLogin.UserDataActivity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class UserDataActivity(accessToken: String) : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
      //  userdata()
    }
/*private fun userdata(){
    (application as DaedoApplication)
        .requestService()
        ?.GetUserInformation(EmailLoginBody.instance.access_token)
        .enqueue(object : Callback<JsonObject>){

        }
}*/
}




