package com.junhyuk.daedo.feed.getCommentList

import android.util.Log
import java.util.*

class time {
    internal fun getTime(
        time: Long
    ) {


    }

    fun formatTimeString(
        YEAR: Int, MONTH: Int, DAY: Int, HOUR: Int, MIN: Int, SEC: Int
    ): String? {
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH] + 1
        val day = cal[Calendar.DAY_OF_MONTH]
        val hour = cal[Calendar.HOUR_OF_DAY]
        val min = cal[Calendar.MINUTE]
        val sec = cal[Calendar.SECOND]
        Log.d("lsec", "lsec : $sec")
        Log.d("lmin", "lmin : $min")
        Log.d("lhour", "lhour : $hour")
        Log.d("lday", "lday : $day")
        Log.d("lmonth", "lmonth : $month")
        var msg: String? = null
        if (year != YEAR) {
            val result = year - YEAR
            msg = result.toString() + "년 전"
        } else if (month != MONTH && year == YEAR) {
            msg = if (min < MIN) {
                val result = MONTH - month
                result.toString() + "분 전"
            } else {
                val result = month - MONTH
                result.toString() + "분 전"
            }
        } else if (day != DAY &&
            month == MONTH && year == YEAR
        ) {
            msg = if (day < DAY) {
                val result = DAY - day
                result.toString() + "일 전"
            } else {
                val result = day - DAY
                result.toString() + "일 전"
            }
        } else if (hour != HOUR && day == DAY &&
            month == MONTH && year == YEAR
        ) {
            msg = if (hour < HOUR) {
                val result = HOUR - hour
                result.toString() + "시간 전"
            } else {
                val result = hour - HOUR
                result.toString() + "시간 전"
            }
        } else if (min != MIN && hour == HOUR && day == DAY &&
            month == MONTH && year == YEAR
        ) {
            msg = if (min < MIN) {
                val result = MIN - min
                result.toString() + "분 전"
            } else {
                val result = min - MIN
                result.toString() + "분 전"
            }
        }
        return msg
    }
}