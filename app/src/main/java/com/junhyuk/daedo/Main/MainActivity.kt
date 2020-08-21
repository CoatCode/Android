package com.junhyuk.daedo.Main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.junhyuk.daedo.EmailLogin.Activity.EmailLoginActivity
import com.junhyuk.daedo.R
import com.junhyuk.daedo.SignUp.Activity.SignUpActivity
import kotlinx.android.synthetic.main.activity_main.*

//인트로 엑티비티
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkSelfPermission()
        
        //소셜 로그인 버튼을 클릭 했을 시 EmailLoginActivity 로 이동
        email_login_button.setOnClickListener {
            startActivity(Intent(this, EmailLoginActivity::class.java))
            finish()
        }

        sign_up_button.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

    }

    private fun checkSelfPermission() {

        var temp = ""

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " "
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " "
        }

        if (!TextUtils.isEmpty(temp)) {
            ActivityCompat.requestPermissions(this, temp.trim().split(" ").toTypedArray(), 1)
        }

    }

}