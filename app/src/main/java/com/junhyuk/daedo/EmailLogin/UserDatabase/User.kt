package com.junhyuk.daedo.EmailLogin.UserDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserInformation")

class User{
    @PrimaryKey
    @ColumnInfo(name = "word")
    private var mWord: String? = null

    fun User(word: String) {
        mWord = word
    }

    fun getUser(): String? {
        return mWord
    }
}


