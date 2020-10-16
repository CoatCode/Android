package com.junhyuk.daedo.feed.getCommentList

import java.util.*

class time {
    internal fun getTime(
        time: Long
    ) {



    }
    fun formatTimeString(YEAR : Long, MONTH : Long, DAY : Long
                         , HOUR : Long, MIN : Long, SEC : Long): String?
    {

        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH] + 1
        val day = cal[Calendar.DAY_OF_MONTH]
        val hour = cal[Calendar.HOUR_OF_DAY]
        val min = cal[Calendar.MINUTE]
        val sec = cal[Calendar.SECOND]
        val curTime = System.currentTimeMillis()

        var msg: String? = null
        if (SEC - sec < 0) {
            msg = "방금 전"
        } else if (MIN - min < 0 && HOUR - hour <= 0) {
            val result = MIN - min
            msg = result.toString() + "분 전"
        } else if (HOUR - hour < 0 && DAY - day <= 0) {
            val result = HOUR - hour
            msg = result.toString() + "시간 전"
        } else if (DAY - day < 0 && MONTH - month <= 0) {
            val result = (DAY - day) * -1
            msg = result.toString() + "일 전"
        } else if (day > MONTH) {
            val result = MIN - min
            //msg = diffTime.toString() + "달 전"
        } else {
            val result = YEAR - year
            msg = result.toString()
        }
        return msg
    }
}