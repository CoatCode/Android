package com.junhyuk.daedo.EmailLogin.UserDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserInformation")

class User(@PrimaryKey val idx : Int,
    @ColumnInfo(name = "mWord")
    public var mWord: String? = null,
           val email : String,
           val Username : String,
           val profile : String


)




