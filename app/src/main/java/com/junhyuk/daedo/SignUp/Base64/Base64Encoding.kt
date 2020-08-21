package com.junhyuk.daedo.SignUp.Base64

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class Base64Encoding {

    fun encoding(bitmap: Bitmap): String{
        val base64: String

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        base64 = Base64.encodeToString(byteArray, Base64.DEFAULT)

        return base64
    }

}