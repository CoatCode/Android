package com.junhyuk.daedo.SignUp.Activity

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.Main.MainActivity
import com.junhyuk.daedo.R
import com.junhyuk.daedo.SignUp.Base64.Base64Encoding
import com.junhyuk.daedo.SignUp.Server.SignUpBody
import com.junhyuk.daedo.SignUp.Server.SignUpDialog
import com.junhyuk.daedo.SignUp.Server.SignUpResponse
import com.junhyuk.daedo.SignUp.Server.SignUpService
import com.junhyuk.daedo.SignUp.Sha256.Sha256
import kotlinx.android.synthetic.main.activity_sign_up_name.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream


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

        val intent: Intent = intent //getIntent

        email = intent.extras?.getString("userInfoEmail").toString() //이메일 저장
        password = intent.extras?.getString("userInfoPassword").toString() //password 저장

        val sha256Class: Sha256 = Sha256()

        val encodePassword: String = sha256Class.sha256(password) //password sha256 암호화

        password = encodePassword //password 저장

        Log.d("base64Data", "sha256: $password")

        //갤러리에서 프로필 사진 가져오기
        user_image.setOnClickListener {

            val imageIntent = Intent() //기기 기본 갤러리 접근

            imageIntent.type = "image/*"
            imageIntent.action = Intent.ACTION_GET_CONTENT

            //구글 갤러리 접근
            //intent.setType("image/*");
            startActivityForResult(imageIntent, 101)

        }

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
                userName = input_name.text.toString()
                checkNameMsg()
            }

            override fun afterTextChanged(s: Editable) {
                userName = input_name.text.toString()
                checkNameMsg()
            }
        })

    }

    //네트워크 작업
    private fun setupRetrofit() {

        val signUpService =
            (application as DaedoApplication).retrofit.create(SignUpService::class.java)

        signUpService.requestSignUp(SignUpBody(email, password, userName, base64))
            .enqueue(object : Callback<SignUpResponse> {
                val signUpDialog = SignUpDialog()

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    signUpDialog.connectionFail(this@SignUpNameActivity)
                }

                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {

                    //통신 성공 응답값을 받아옴
                    Log.d("data2", "data: ${response.code()}")

                    val intent = Intent(this@SignUpNameActivity, MainActivity::class.java)

                    signUpDialog.connectionSuccess(
                        response.code(),
                        response.message(),
                        this@SignUpNameActivity,
                        response.errorBody()?.string().toString() ,
                        intent
                    )

                }
            })
    }

    //이름이 입력 될 때 마다 호출되는 함수(이름 형식 검사)
    private fun checkNameMsg() {
        if (input_name.text.toString().isNotEmpty()) {
            Thread {
                runOnUiThread {
                    check_name_text.text = "올바른 이름입니다."
                    check_name_text.setTextColor(getColorStateList(R.color.colorBlue))
                    sign_up_button.setBackgroundResource(R.drawable.login_button)
                    sign_up_button.isEnabled = true
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

    //권한 허용 확인
    override fun onRequestPermissionsResult(

        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray

    ) {

        if (requestCode == 1) {

            val length = permissions.size

            for (i in 0..length) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("MainActivity", "권환 허용" + permissions[i])
                }
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 이미지 파일이 넘어 왔을 경우
        if (requestCode == 101 && resultCode == RESULT_OK) {
            try {
                val inputStream: InputStream? = contentResolver.openInputStream(data?.data!!)

                val bm = BitmapFactory.decodeStream(inputStream)

                Log.d("data3", "data: ${data.data}")

                inputStream?.close()

                user_image.setImageBitmap(bm)

                base64 = "data:image/jpeg;base64,${base64Encoding.encoding(bm)}"  //Base64 인코딩

                Log.d("base64Data", "base64: $base64")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        } 

        //어떤 파일도 넘어오지 않았을 때
        else if (requestCode == 101 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
        }
    }
}

