package com.junhyuk.daedo.main.bottomItem.home.module

class FeedTime {

    /** 몇분전, 방금 전,  */
    private object TimeData {
        const val SEC = 60
        const val MIN = 60
        const val HOUR = 24
        const val DAY = 30
        const val MONTH = 12
    }

    fun calFeedTime(time: Long): String{

        val curTime = System.currentTimeMillis()
        var diffTime = (curTime - time) / 1000
        val msg: String

        when {
            diffTime < TimeData.SEC -> {
                msg = "방금 전"
            }
            TimeData.SEC.let { diffTime /= it; diffTime } < TimeData.MIN -> {
                msg = diffTime.toString() + "분 전"
            }
            TimeData.MIN.let { diffTime /= it; diffTime } < TimeData.HOUR -> {
                msg = diffTime.toString() + "시간 전"
            }
            TimeData.HOUR.let { diffTime /= it; diffTime } < TimeData.DAY -> {
                msg = diffTime.toString() + "일 전"
            }
            TimeData.DAY.let { diffTime /= it; diffTime } < TimeData.MONTH -> {
                msg = diffTime.toString() + "달 전"
            }
            else -> {
                msg = diffTime.toString() + "년 전"
            }
        }
        return msg

    }
}