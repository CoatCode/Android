package com.junhyuk.daedo.feed.comment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.R
import com.junhyuk.daedo.intro.Intro
import kotlinx.android.synthetic.main.activity_email_login.*
import kotlinx.android.synthetic.main.fragment_home.*

/*
* - 엑티비티: 로그인 엑티비티(이메일, 비밀번호)
* - 담당자: 한승재
* - 수정 날짜: 2020.08.30
*/
class SendCommentActivity : AppCompatActivity() {

    private var comment: String = ""
    private val sendComment = com.junhyuk.daedo.feed.comment.SendComment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        //엑션바 숨기기
        val actionBar = supportActionBar
        actionBar?.hide()

        //로그인 xml 에서 우측 상단 x버튼 클릭
        x_button.setOnClickListener {
            startActivity(Intent(this, Intro::class.java))
            finish()
        }

        //사용자가 이메일 창과 패스워드 창에 값을 넣고 로그인 버튼을 입력
        write_comment.setOnClickListener {
            comment = this@SendCommentActivity.edit_comment.text.toString()

            sendComment.sendComment(comment,application, this)
            Log.d("password","password:$comment")


        }
    }
}





