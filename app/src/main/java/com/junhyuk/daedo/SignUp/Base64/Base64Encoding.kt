package com.junhyuk.daedo.SignUp.Base64

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.junhyuk.daedo.R
import java.io.ByteArrayOutputStream

class Base64Encoding {

    fun encoding(context: Context): String{
        val base64: String

        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.user_profile)

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        base64 = Base64.encodeToString(byteArray, Base64.DEFAULT)

        return base64
    }

}