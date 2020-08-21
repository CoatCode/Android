package com.junhyuk.daedo.SignUp.Server

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import cn.pedant.SweetAlert.SweetAlertDialog

class SignUpDialog {

    internal fun connectionSuccess(
        responseCode: Int,
        responseMsg: String,
        context: Context,
        responseBody: String,
        intent: Intent
    ) {
        //통신 성공
        when (responseCode) {
            201 -> {
                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("회원가입이 완료 되었습니다")
                    .setConfirmClickListener {
                        startActivity(context, intent, null)
                        (context as Activity).finish()
                    }.show()

            }

            400 -> {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("실패!")
                dialog.setMessage(
                    "통신에 실패하였습니다. 다시 확인해" + "\n" +
                            "status: " + responseCode + " msg: " + responseBody
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