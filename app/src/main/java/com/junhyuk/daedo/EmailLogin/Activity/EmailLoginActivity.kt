package com.junhyuk.daedo.EmailLogin.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Oauth.Oauth
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import com.junhyuk.daedo.Main.MainActivity
import com.junhyuk.daedo.R
import kotlinx.android.synthetic.main.activity_email_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//retrofit2 라이브러리를 사용하여 이메일 액티비티 구성
class EmailLoginActivity : AppCompatActivity() {
    //Oauth 호출을 위한 변수
    var api = Oauth.getInstance()
    //사용자의 이메일을 입력받기 위한 변수
    private var email : String = String()
    //사용자의 패스워드를 입력받기 위한 변수
    private var PW : String = String()

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
            //email변수에 사용자가 입력한 이메일이 들어간다
            email = editTextTextEmailAddress.text.toString()
            //PW변수에 사용자가 입력한 패스워드가 들어간다
            PW = editTextTextPassword.text.toString()
            //request 함수 호출
            request()
        }
    }
    private fun request() {

        (application as DaedoApplication)
            .requestService()
            api.sendLoginInformation(EmailLoginBody(email,PW))
            .enqueue(object : Callback<EmailLoginBody> {
                override fun onResponse(
                    call: Call<EmailLoginBody>,
                    response: Response<EmailLoginBody>
                ) {
                    //서버 코드가 200일시 다음 화면으로 이동
                    if (response.code() == 200){
                        EmailLoginBody.instance =  response.body()
                        Log.d("body","body"+response.body())
                        Toast.makeText(applicationContext,"로그인 성공",Toast.LENGTH_LONG).show()
                    }
                    //서버 코드가 401일시 에러발생후 사용자에게 로그인 실패를 띄움
                    else if (response.code() == 401){
                        Log.d("Email", "data: ${response.errorBody()?.string().toString()}")
                        Toast.makeText(applicationContext,"로그인 실패",Toast.LENGTH_LONG).show()
                    }

                }
                //서버 통신 실패
                override fun onFailure(call: Call<EmailLoginBody>, t: Throwable) {
                    Log.d("failure","falid")
                    Toast.makeText(applicationContext,"서버 연결 실패", Toast.LENGTH_LONG).show()
                }


            })
    }
}
