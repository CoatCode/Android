package com.junhyuk.daedo.dataBase.userDataHandler

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.dataBase.userDatabase.UserTable
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.main.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//유저 정보를 받아오기 위해 서버와 통신하는 클래스
class  UserDataClass {
  internal fun getUserData(
      getApplication: Application,
      context: Context
  ) {
      //token 변수에 로그인시 발급받은 access_token 을 담는다
      val token : String = EmailLoginBody.instance!!.access_token
        (getApplication as DaedoApplication)
        .requestService()
        //토큰 타입 지정해주고 Header 에 값을 보낸다
        ?.getUserInformation("Bearer $token")
        ?.enqueue(object : Callback<UserInformation> {
            override fun onResponse(call: Call<UserInformation>, response: Response<UserInformation>) {
                //서버로 받은 코드가 200일시 AddDatabase 클래스 호출
                if (response.code() == 200){
                    UserInformation.instance=response.body()
                    //val addDataBase = AddDatabase(context)
                    CoroutineScope(Dispatchers.IO).launch {
                        val addUser = UserTable(1,null, UserInformation.instance!!.id ,UserInformation.instance?.username, UserInformation.instance?.email,
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
                        withContext(Dispatchers.Main){
                            val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                            dialog.setCancelable(false)

                            dialog.setTitleText("로그인 성공")
                                .setConfirmClickListener {
                                    ContextCompat.startActivity(context, Intent(context, MainActivity::class.java), null)
                                    (context as Activity).finish()
                                    ActivityCompat.finishAffinity(context)
                                    dialog.dismiss()
                                }
                                .show()

                        }
                    }
                }
            }
            override fun onFailure(call: Call<UserInformation>, t: Throwable) {

            }

        })

  }
}






