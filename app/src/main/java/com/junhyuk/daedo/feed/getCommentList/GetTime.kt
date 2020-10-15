package com.junhyuk.daedo.feed.getCommentList

import androidx.appcompat.app.AppCompatActivity

class GetTime : AppCompatActivity() {
    internal fun getTime(
        commentWriteTime: String
    ) {
        commentWriteTime.toLong()
        formatTimeString(commentWriteTime.toLong())
    }
    private fun formatTimeString(regTime: Long): String? {
        val curTime = System.currentTimeMillis()
        var diffTime = (curTime - regTime) / 1000
        var msg: String? = null
        if (diffTime < 60) {
            msg = "방금 전"
        } else if (60.let { diffTime /= it; diffTime } < 60) {
            msg = diffTime.toString() + "분 전"
        } else if (60.let { diffTime /= it; diffTime } < 24) {
            msg = diffTime.toString() + "시간 전"
        } else if (24.let { diffTime /= it; diffTime } < 30) {
            msg = diffTime.toString() + "일 전"
        } else if (30.let { diffTime /= it; diffTime } < 12) {
            msg = diffTime.toString() + "달 전"
        } else {
            msg = diffTime.toString() + "년 전"
        }
        return msg
    }
}