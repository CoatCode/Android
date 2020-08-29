package com.junhyuk.daedo.EmailLogin.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Oauth.Oauth
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import com.junhyuk.daedo.Main.MainActivity
import com.junhyuk.daedo.R
import com.junhyuk.daedo.SignUp.Activity.SignUpDialog
import kotlinx.android.synthetic.main.activity_email_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//retrofit2 라이브러리를 사용하여 이메일 액티비티 구성
class EmailLoginActivity : AppCompatActivity() {
    var sha512class = com.junhyuk.daedo.SignUp.Sha512.Sha512()
    //Oauth 호출을 위한 변수
    var api = Oauth.getInstance()


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
            val login = EmailLoginBody().apply {
                //입력받은 이메일을 Email 변수에 담아준다
                Email = this@EmailLoginActivity.editTextTextEmailAddress.text.toString()
                //입력받은 비밀번호를 PW 변수에 담아준다
                PW = editTextTextPassword.text.toString()

                //사용자 비밀번호 암호화
                val LockPW: String = sha512class.sha512(PW)
                PW = LockPW
                //request 함수 호출
               request(Email,PW)
            }



        }
    }
    private fun request(email : String,PW : String ) {

            (application as DaedoApplication)
                .requestService()
            api.sendLoginInformation(EmailLoginBody(email,PW))

                .enqueue(object : Callback<EmailLoginBody> {
                    val signUpDialog = SignUpDialog()
                    override fun onResponse(
                        call: Call<EmailLoginBody>,
                        response: Response<EmailLoginBody>
                    ) {

                        //서버 코드가 200일시 다음 화면으로 이동
                        if (response.code() == 200) {
                            Log.d("failure", "faild:$PW")
                            //서버로부터 받은 정보들을 EmailLoginBody 변수에 담아준다
                            EmailLoginBody.instance = response.body()
                            Log.d("body", "body" + response.body())
                        }
                        //서버 코드가 401일시 에러발생후 사용자에게 로그인 실패를 띄움
                        else if (response.code() == 401) {

                            Log.d("Email", "data: ${response.errorBody()?.string().toString()}")

                        }

                    }

                    //서버 통신 실패
                    override fun onFailure(call: Call<EmailLoginBody>, t: Throwable) {


                    }


                })
        }

    fun connectionFail(context: Context, sweetAlertDialog: SweetAlertDialog) {

        sweetAlertDialog.dismiss()
        val dialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)

        dialog.setCancelable(false)

        dialog.setTitleText("서버 통신에 실패하였습니다.")
            .setConfirmClickListener {
                dialog.dismiss()
            }
            .setContentText("네트워크 연결을 확인해 주세요.")
            .show()
    }


}
