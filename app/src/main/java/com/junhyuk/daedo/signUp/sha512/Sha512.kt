package com.junhyuk.daedo.signUp.sha512

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

class Sha512 {

    //비밀번호 sha512 암호화
    internal fun sha512(password: String): String {

        val digest = MessageDigest.getInstance("SHA-512")
        digest.reset()
        digest.update(password.toByteArray())
        var getSha512: String = java.lang.String.format("%0128x", BigInteger(1, digest.digest()))

        getSha512 = getSha512.toUpperCase(Locale.ROOT)

        Log.d("sha", "sha:  $getSha512")
        return getSha512
    }
}