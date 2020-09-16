package com.junhyuk.daedo.Main.ui.post.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.R

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        //엑션바 숨기기
        val actionBar = supportActionBar
        actionBar?.hide()
    }
}