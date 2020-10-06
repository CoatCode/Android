package com.junhyuk.daedo.feed.comment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/*
* - 엑티비티: 댓글 엑티비티(댓글 작성)
* - 담당자: 한승재
* - 수정 날짜: 2020.10.05
*/
class SendCommentActivity(private val test_comment : String = "") : AppCompatActivity(){

    private val sendComment = SendComment()
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("send","send$test_comment")
        super.onCreate(savedInstanceState)
        //액션바 숨기기
        val actionBar = supportActionBar
        actionBar?.hide()
        sendComment.sendComment(test_comment,application, this)
        Log.d("password","password:$test_comment")

    }


}





