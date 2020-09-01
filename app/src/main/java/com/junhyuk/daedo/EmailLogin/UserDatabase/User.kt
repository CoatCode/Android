package com.junhyuk.daedo.EmailLogin.UserDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "User")

class User(
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "profile") var profile: String?
){
    constructor(): this("","",null)
}