package com.junhyuk.daedo.EmailLogin.UserDatabase

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class UserTable{

@PrimaryKey
var Email = 0

@ColumnInfo(name = "first_name")
var profile: String? = null

@ColumnInfo(name = "last_name")
var username: String? = null
}