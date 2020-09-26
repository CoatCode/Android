package com.junhyuk.daedo.dataBase.userDataHandler

import android.content.Context
import android.util.Log
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.dataBase.userDatabase.UserTable

class AddDatabase(val context: Context) : Thread() {
    override fun run() {
        //서버로 부터 받은 유저 데이터를 DB에 저장
        val addUser = UserTable(2,null, UserInformation.instance?.email, UserInformation.instance?.username, UserInformation.instance?.profile)
        UserDataBase.getDatabase(context)!!
            .userDao()
            ?.insert(addUser)
        //DB에 저장된 유저 정보를 불러오는 코드
        val callUserInfor = UserDataBase.getDatabase(context)!!
            .userDao()
            ?.getAllUser()
        if (callUserInfor != null) {
            for(i in callUserInfor){ Log.d("bookList", "${i.idx} | ${i.doNotTouch} ${i.Username} | ${i.email} | ${i.profile}") }
        }


    }
}