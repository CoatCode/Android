package com.junhyuk.daedo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.EmailLogin.EmailLoginActivity
import kotlinx.android.synthetic.main.activity_main.*

//인트로 엑티비티
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        //소셜 로그인 버튼을 클릭 했을 시 EmailLoginActivity 로 이동
        email_login_button.setOnClickListener {
            startActivity(Intent(this, EmailLoginActivity::class.java))
            finish()
        }



    }

}