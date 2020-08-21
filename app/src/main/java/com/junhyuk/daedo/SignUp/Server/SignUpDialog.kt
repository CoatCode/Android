package com.junhyuk.daedo.SignUp.Server

import android.content.Context
import androidx.appcompat.app.AlertDialog

class SignUpDialog {

    fun connectionSuccess(responseCode: Int, responseMsg: String, context: Context) {
        //통신 성공
        when (responseCode) {
            201 -> {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("성공!")
                dialog.setMessage("status: $responseCode msg: $responseMsg 이메일이 전송 되었습니다.")
                dialog.show()
            }

            400 -> {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("실패!")
                dialog.setMessage(
                    "통신에 실패하였습니다. 다시 확인해" + "\n" +
                            "status: " + responseCode + " msg: " + responseMsg
                )
                dialog.show()
            }

            else -> {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("실패!")
                dialog.setMessage(
                    "통신에 실패하였습니다. 다시!!" + "\n" +
                            "status: " + responseCode + " msg: " + responseMsg
                )
                dialog.show()
            }
        }
    }

    fun connectionFail(context: Context) {
        //통신 실패
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("실패!")
        dialog.setMessage("통신에 실패하였습니다. fail")
        dialog.show()
    }

}