package com.junhyuk.daedo.SignUp.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.Main.MainActivity
import com.junhyuk.daedo.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Matcher


/*
* - 엑티비티: 회원가입 엑티비티(이메일, 비밀번호)
* - 담당자: 양준혁
* - 수정 날짜: 2020.08.24
*/

//회원 아이디와 비밀번호를 입력받아 SignUpName 엑티비티로 intent 시키는 엑티비티
class SignUpActivity : AppCompatActivity() {

    //이메일 형식과 비밀번호 형식이 맞는지 검사할 때 사용하는 변수들
    var checkEmail: Boolean = false
    var checkPassword: Boolean = false

    //이메일과 비밀번호를 다음 엑티비티로 전송할 때 정보를 저장하는 변수
    var email: String = ""
    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //x버튼 을 눌렀을 시
        x_button_sign_up.setOnClickListener {
            //메인으로 돌아감
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        //이메일 형식이 맞으면 버튼 클릭이 가능 하도록 하는 코드
        editTextTextEmailAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkEmail()
            }

            override fun afterTextChanged(s: Editable) {
                checkEmail()
            }
        })

        //비밀번호 형식이 맞으면 버튼 클릭이 가능 하도록 하는 코드
        editTextTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkPassword()
            }

            override fun afterTextChanged(s: Editable) {
                checkPassword()
            }
        })

        //다음 버튼을 눌렀을 시
        next_button.setOnClickListener {

            //이메일과 비밀번호를 저장
            email = editTextTextEmailAddress.text.toString()
            password = editTextTextPassword.text.toString()

            //인텐트 변수 선언
            val intent = Intent(this, SignUpNameActivity::class.java)

            //이메일 비밀번호를 다음 엑티비티에다가 put
            intent.putExtra("userInfoEmail", email)
            intent.putExtra("userInfoPassword", password)

            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    //이메일 형식 체크
    private fun isEmail(email: String): Boolean {
        var returnValue = false
        val pattern = Patterns.EMAIL_ADDRESS
        val m: Matcher = pattern.matcher(email)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue
    }

    private fun checkButton(checkEmail: Boolean, checkPassword: Boolean){

        this.checkEmail = checkEmail
        this.checkPassword = checkPassword

        Log.d("data1", "data: $checkEmail")
        Log.d("data1", "data: $checkPassword")

        if(checkEmail && checkPassword){

            next_button.setBackgroundResource(R.drawable.login_button)
            next_button.isEnabled = true

        }else{

            next_button.setBackgroundResource(R.drawable.login_button_false)
            next_button.isEnabled = false

        }
    }

    private fun checkEmail(){
        if (isEmail(editTextTextEmailAddress.text.toString())) {
            Thread {
                runOnUiThread {
                    check_email_text.text = "올바른 이메일 형식입니다."
                    check_email_text.setTextColor(getColorStateList(R.color.colorBlue))
                    checkEmail = true
                    checkButton(checkEmail, checkPassword)
                }
            }.start()
        } else {
            Thread {
                runOnUiThread {
                    check_email_text.text = "올바르지 않은 이메일 형식입니다."
                    check_email_text.setTextColor(getColorStateList(R.color.colorRed))
                    checkEmail = false
                    checkButton(checkEmail, checkPassword)
                }
            }.start()
        }
    }

    private fun checkPassword(){
        if (editTextTextPassword.text.toString().isNotEmpty() && editTextTextPassword.text.toString().length >= 6) {
            Thread {
                runOnUiThread {
                    check_password_text.text = "올바른 비밀번호 형식입니다."
                    check_password_text.setTextColor(getColorStateList(R.color.colorBlue))
                    checkPassword = true
                    checkButton(checkEmail, checkPassword)
                }
            }.start()
        } else {
            Thread{
                runOnUiThread {
                    check_password_text.text = "올바르지 않은 비밀번호 형식입니다."
                    check_password_text.setTextColor(getColorStateList(R.color.colorRed))
                    checkPassword = false
                    checkButton(checkEmail, checkPassword)
                }
            }.start()
        }
    }
}