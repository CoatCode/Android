package com.junhyuk.daedo.feed.comment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_home.*

/*
* - 엑티비티: 댓글 엑티비티(댓글 작성)
* - 담당자: 한승재
* - 수정 날짜: 2020.10.05
*/
class SendCommentActivity : AppCompatActivity() {

    private var comment: String = ""
    private val sendComment = SendComment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //엑션바 숨기기
        val actionBar = supportActionBar
        actionBar?.hide()


            Log.d("button_test","button_test")
            comment = this.edit_comment.text.toString()
            sendComment.sendComment(comment,application, this)
            Log.d("password","password:$comment")

    }
}





