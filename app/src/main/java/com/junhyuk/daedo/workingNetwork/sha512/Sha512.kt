package com.junhyuk.daedo.workingNetwork.sha512

import java.math.BigInteger
import java.security.MessageDigest

class Sha512 {

    //비밀번호 sha512 암호화
    internal fun sha512(password: String): String {

        val digest = MessageDigest.getInstance("SHA-512")
        digest.reset()
        digest.update(password.toByteArray())
        return java.lang.String.format("%0128x", BigInteger(1, digest.digest()))
    }
}