package com.junhyuk.daedo.dataBase.userDataHandler

import android.app.Application
import android.content.Context
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//유저 정보를 받아오기 위해 서버와 통신하는 클래스
class UserDataActivity {
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
                    val addDataBase = AddDatabase(context)
                    //addDatabase 호출
                    addDataBase.start()
                }
            }
            override fun onFailure(call: Call<UserInformation>, t: Throwable) {

            }

        })

  }
}






