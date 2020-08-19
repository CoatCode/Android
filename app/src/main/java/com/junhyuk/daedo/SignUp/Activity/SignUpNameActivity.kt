package com.junhyuk.daedo.SignUp.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.R
import com.junhyuk.daedo.SignUp.Base64.Base64Encoding
import com.junhyuk.daedo.SignUp.Server.SignUp
import com.junhyuk.daedo.SignUp.Server.SignUpBody
import com.junhyuk.daedo.SignUp.Server.SignUpService
import com.junhyuk.daedo.SignUp.Server.WorkingSignUp
import kotlinx.android.synthetic.main.activity_sign_up_name.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//이름을 입력하고 서버에 전송을 담당하는 엑티비티
open class SignUpNameActivity : AppCompatActivity() {

    private var email: String = ""
    private var password: String = ""
    private var userName: String = ""
    private var base64: String = ""
    private val base64Encoding = Base64Encoding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_name)

        val intent: Intent = intent

        email = intent.extras?.getString("userInfoEmail").toString()
        password = intent.extras?.getString("userInfoPassword").toString()

        //Base64 인코딩
        base64 = base64Encoding.encoding(applicationContext)

        //sign_up 버튼을 누르면 모든 값을 서버로 전송
        sign_up_button.setOnClickListener {
            setupRetrofit()
        }

        //뒤로가기 버튼을 눌렀을 때
        back_button_sign_up.setOnClickListener {
            //뒤로 돌아감
            onBackPressed()
        }

        //이름이 null 인지 아닌지 판단
        input_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkNameMsg()
            }

            override fun afterTextChanged(s: Editable) {
                checkNameMsg()
            }
        })

    }

    //네트워크 작업
    private fun setupRetrofit() {

        val signUpService =
            (application as DaedoApplication).retrofit.create(SignUpService::class.java)

        signUpService.requestSignUp(SignUpBody(email, password, userName, base64))
            .enqueue(object : Callback<SignUp> {
                val workingSignUp = WorkingSignUp()

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    workingSignUp.connectionFail(this@SignUpNameActivity)
                }

                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {

                    //통신 성공 응답값을 받아옴

                    Log.d("data2", "data: ${response.code()}")

                    workingSignUp.connectionSuccess(
                        response.code(),
                        response.message(),
                        this@SignUpNameActivity
                    )
                }
            })
    }

    //이름이 입력 될 때 마다 호출되는 함수(이름 형식 검사)
    private fun checkNameMsg(){
        if (input_name.text.toString().isNotEmpty()) {
            Thread {
                runOnUiThread {
                    check_name_text.text = "올바른 이름입니다."
                    check_name_text.setTextColor(getColorStateList(R.color.colorBlue))
                    sign_up_button.setBackgroundResource(R.drawable.login_button)
                    sign_up_button.isEnabled = true
                    userName = input_name.text.toString()
                }
            }.start()
        } else {
            Thread {
                runOnUiThread {
                    check_name_text.text = "올바르지 않은 이름입니다."
                    check_name_text.setTextColor(getColorStateList(R.color.colorRed))
                    sign_up_button.setBackgroundResource(R.drawable.login_button_false)
                    sign_up_button.isEnabled = false
                }
            }.start()
        }
    }
}