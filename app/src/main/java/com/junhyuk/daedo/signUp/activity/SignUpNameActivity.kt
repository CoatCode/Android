package com.junhyuk.daedo.signUp.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.signUp.rotateImage.RotateImage
import com.junhyuk.daedo.signUp.workingRetrofit.SetupRetrofit
import com.junhyuk.daedo.workingNetwork.sha512.Sha512
import kotlinx.android.synthetic.main.activity_sign_up_name.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream


/*
* - 엑티비티: 회원가입 엑티비티(프로필 사진, 이름)
* - 담당자: 양준혁
* - 수정 날짜: 2020.08.24
*/


//이름을 입력하고 서버에 전송을 담당하는 엑티비티
open class SignUpNameActivity : AppCompatActivity() {

    //서버로 보낼 데이터 변수
    private var email: String = ""
    private var password: String = ""
    private var userName: String = ""
    private var profileImage: RequestBody? = null
    private var imageName: String = ""


    private val setupRetrofit = SetupRetrofit() //retrofit setup
    private val rotateImageClass = RotateImage() //이미지 회전
    private val sha512Class = Sha512() //sha512 인코딩

    //onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_name)
        //엑션바 숨기기
        val actionBar = supportActionBar
        actionBar?.hide()
        //intent 데이터
        val intent: Intent = intent //이메일 비밀번호 인텐트 데이터
        email = intent.extras?.getString("userInfoEmail").toString() //이메일 저장
        password = intent.extras?.getString("userInfoPassword").toString() //password 저장
        //sha256 암호화
        val encodePassword: String = sha512Class.sha512(password) //password sha512 암호화
        password = encodePassword //password 저장

        Log.d("password", "Sha512: $encodePassword")

        //갤러리에서 프로필 사진 가져오기
        user_image.setOnClickListener {
            val imageIntent = Intent() //구글 갤러리 접근 intent 변수
            //구글 갤러리 접근
            imageIntent.type = "image/*"
            imageIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(imageIntent, 101)

        }

        //뒤로 가기 버튼 눌렀을 때
        back_button_sign_up.setOnClickListener {
            //뒤로 돌아감
            onBackPressed()
        }

        //sign_up 버튼을 누르면 모든 값을 서버로 전송
        sign_up_button.setOnClickListener {
            setupRetrofit.setupRetrofit(email, password, userName, profileImage, imageName, application, this)
        }

        //이름이 null 인지 아닌지 판단
        input_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
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

    //이름이 입력 될 때 마다 호출되는 함수(이름 형식 검사)
    private fun checkNameMsg() {
        if (input_name.text.toString().isNotBlank()) {
            check_name_text.text = "올바른 이름입니다."
            check_name_text.setTextColor(getColorStateList(R.color.colorBlue))
            sign_up_button.setBackgroundResource(R.drawable.login_button)
            sign_up_button.isEnabled = true
        } else {
            check_name_text.text = "올바르지 않은 이름입니다."
            check_name_text.setTextColor(getColorStateList(R.color.colorRed))
            sign_up_button.setBackgroundResource(R.drawable.login_button_false)
            sign_up_button.isEnabled = false
        }
    }

    //갤러리에서 넘어온 이미지 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val returnUri: Uri
        val returnCursor: Cursor?

        // 이미지 파일이 넘어 왔을 경우
        if (requestCode == 101 && resultCode == RESULT_OK) {
            try {

                //이미지 정보
                returnUri = data?.data!!

                //이미지 파일 받아오기
                val inputStream = contentResolver.openInputStream(returnUri) //input 스트림
                var bm: Bitmap = BitmapFactory.decodeStream(inputStream) //비트맵 변환
                bm = rotateImageClass.rotateImage(data.data!!, bm, contentResolver) //이미지 회전
                val bos = ByteArrayOutputStream()
                bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                profileImage = RequestBody.create(MultipartBody.FORM, bos.toByteArray())
                inputStream?.close()

                //화면에 이미지 표시
                Glide.with(this)
                    .load(bm)
                    .thumbnail(Glide.with(applicationContext).load(R.raw.loading))
                    .override(1000)
                    .transform(CenterCrop(), RoundedCorners(1000000000))
                    .into(user_image)

                returnCursor = contentResolver.query(returnUri, null, null, null, null)

                //이미지 이름
                val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                returnCursor.moveToFirst()
                imageName = returnCursor.getString(nameIndex)

                returnCursor.close()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //어떤 파일도 넘어오지 않았을 때
        else if (requestCode == 101 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
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
}