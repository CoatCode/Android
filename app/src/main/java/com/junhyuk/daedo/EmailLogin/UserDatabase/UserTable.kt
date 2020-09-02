package com.junhyuk.daedo.EmailLogin.UserDatabase

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
//user의 정보를 담는 UserTable
class UserTable{

@PrimaryKey
var Email = 0

@ColumnInfo(name = "first_name")
var profile: String? = null

@ColumnInfo(name = "last_name")
var username: String? = null
}