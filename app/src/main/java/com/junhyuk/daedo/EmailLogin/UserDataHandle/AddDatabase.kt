package com.junhyuk.daedo.EmailLogin.UserDataHandle

import android.content.Context
import com.junhyuk.daedo.EmailLogin.UserDatabase.UserDataBase
import com.junhyuk.daedo.EmailLogin.UserDatabase.UserTable

class AddDatabase(val context: Context) : Thread() {
    override fun run() {
        //서버로 부터 받은 유저 데이터를 DB에 저장
        val AddUser = UserTable(1,null, UserInformation.instance?.email, UserInformation.instance?.username, UserInformation.instance?.profile)
        UserDataBase.getDatabase(context)!!
            .UserDao()
            ?.insert(AddUser)
        //DB에 저장된 유저 정보를 불러오는 코드
        val CallUserInfor = UserDataBase.getDatabase(context)!!
            .UserDao()
            ?.getAllUser()
    }
}