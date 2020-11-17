
package com.junhyuk.daedo.dataBase.userDataHandler

import android.content.Context
import android.util.Log
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.dataBase.userDatabase.UserTable

class AddDatabase(val context: Context) : Thread() {
    override fun run() {
        //서버로 부터 받은 유저 데이터를 DB에 저장
        val addUser = UserTable(3,null, UserInformation.instance!!.id ,UserInformation.instance?.email, UserInformation.instance?.username,
            UserInformation.instance?.profile, UserInformation.instance?.description, UserInformation.instance!!.followers,UserInformation.instance!!.following)
        UserDataBase.getDatabase(context)!!
            .userDao()
            ?.insert(addUser)
        //DB에 저장된 유저 정보를 불러오는 코드
        val callUserInformation = UserDataBase.getDatabase(context)!!
            .userDao()
            ?.getAllUser()
        Log.d("get","get$")
        if (callUserInformation != null) {
            for(i in callUserInformation){ Log.d("UserDB", "${i.idx} | ${i.doNotTouch} | ${i.id} | ${i.Username} " +
                    "| ${i.email} | ${i.profile} | ${i.followers} | ${i.following}") }
        }


    }
}