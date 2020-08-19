package com.junhyuk.daedo.EmailLogin.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import com.junhyuk.daedo.Main.MainActivity
import com.junhyuk.daedo.R
import kotlinx.android.synthetic.main.activity_email_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailLoginActivity : AppCompatActivity() {
    private var email : String = String()
    private var PW : String = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)
        x_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        login_button.setOnClickListener{
            email = editTextTextEmailAddress.text.toString()
            PW = editTextTextPassword.text.toString()
            request()
        }
    }
    private fun request() {

        (application as DaedoApplication)
            .requestService()
            ?.sendLoginInformation(EmailLoginBody(email,PW))
            ?.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Log.d("success", "response" +response)
                    Toast.makeText(applicationContext,"로그인 성공",Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.d("failure","falid"+call)
                    Toast.makeText(applicationContext,"로그인 실패",Toast.LENGTH_LONG).show()
                }


            })
    }
}