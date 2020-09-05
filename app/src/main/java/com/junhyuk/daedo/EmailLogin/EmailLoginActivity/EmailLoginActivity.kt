package com.junhyuk.daedo.EmailLogin.EmailLoginActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.EmailLogin.Oauth.Oauth
import com.junhyuk.daedo.Main.MainActivity
import com.junhyuk.daedo.R
import kotlinx.android.synthetic.main.activity_email_login.*
/*
* - 엑티비티: 로그인 엑티비티(이메일, 비밀번호)
* - 담당자: 한승재
* - 수정 날짜: 2020.08.30
*/
class EmailLoginActivity : AppCompatActivity() {

    private var sha512class = com.junhyuk.daedo.SignUp.Sha512.Sha512()
    //Oauth 호출을 위한 변수
    var api = Oauth.getInstance()
    private val getEmailLogin = com.junhyuk.daedo.EmailLogin.EmailLoginActivity.getEmailLogin()
    private val getdata = com.junhyuk.daedo.EmailLogin.UserDataHandle.UserDataActivity()
    //사용자의 이메일을 받을 변수
    private var Email : String = ""
    //사용자의 비밀번호를 받을 변수
    private var PW : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        //로그인 xml에서 우측 상단 x버튼 클릭
        x_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        //사용자가 이메일 창과 패스워드 창에 값을 넣고 로그인 버튼을 입력
        login_button.setOnClickListener{
                //사용자의 이메일을 Email 변수에 담아준다
                Email = this@EmailLoginActivity.editTextTextEmailAddress.text.toString()
                //입력받은 비밀번호를 PW 변수에 담아준다
                PW = editTextTextPassword.text.toString()
                //사용자 비밀번호 암호화
                val LockPW: String = sha512class.sha512(PW)
                PW = LockPW
            //getEmailLogin 클래스로 사용자가 입력한 값 전달
            getEmailLogin.getEmailLogin(Email,PW,application,this)
            //getdata.GetUserData(application)

        }
    }
}





