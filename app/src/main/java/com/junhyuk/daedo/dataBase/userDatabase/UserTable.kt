package com.junhyuk.daedo.dataBase.userDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//User table
@Entity(tableName = "UserInformation")

class UserTable(@PrimaryKey val idx : Int,
                @ColumnInfo(name = "doNottouch") var doNotTouch: String? = null,
                val id : Int,
                val email : String?,
                val Username : String?,
                val profile : String?,
                val followers : String,
                val following : String



)




