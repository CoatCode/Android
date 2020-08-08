package com.junhyuk.daedo

import Daedo.EmailLogin.EmailLoginActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email_login_button.setOnClickListener {
            startActivity(Intent(this, EmailLoginActivity::class.java))
            finish()
        }



    }

}