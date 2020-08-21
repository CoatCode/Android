package com.junhyuk.daedo.SignUp.Sha256

import java.math.BigInteger
import java.security.MessageDigest

class Sha256 {

    //비밀번호 sha256 암호화
    internal fun sha256(password: String): String {

        val digest = MessageDigest.getInstance("SHA-256")
        digest.reset()
        digest.update(password.toByteArray())
        return java.lang.String.format("%064x", BigInteger(1, digest.digest()))

    }

}