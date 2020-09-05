package com.junhyuk.daedo.EmailLogin.UserDataActivity

import android.app.Application
import android.content.Context
import android.util.Log
import com.junhyuk.daedo.Application.DaedoApplication
import com.junhyuk.daedo.EmailLogin.Server.EmailLoginBody
import com.junhyuk.daedo.EmailLogin.UserDatabase.User
import com.junhyuk.daedo.EmailLogin.UserDatabase.UserDataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//유저 정보를 받아오기 위해 서버와 통신하는 클래스
class UserDataActivity {
  internal fun GetUserData(
      getApplication: Application,
      context: Context
  ) {
      val token : String = EmailLoginBody.instance!!.access_token
        Log.d("token", "token=$token")
        (getApplication as DaedoApplication)
        .requestService()
        ?.GetUserInformation("Bearer $token",null)
        ?.enqueue(object : Callback<UserInformation> {
            override fun onResponse(call: Call<UserInformation>, response: Response<UserInformation>) {
                if (response.code() == 200){
                    UserInformation.instance=response.body()
                    val addBook = AddBook(context)
                    addBook.start()

                }
                Log.d("stoken","token="+EmailLoginBody.instance?.access_token)
                Log.d("stoken","stoken$token")
            }

            override fun onFailure(call: Call<UserInformation>, t: Throwable) {
                Log.d("ftoken","token="+EmailLoginBody.instance?.access_token)
            }

        })

  }
}
class AddBook(val context: Context) : Thread() {
    override fun run() {
        val book = User(1, null, UserInformation.instance?.email, UserInformation.instance?.username, UserInformation.instance?.profile)
        UserDataBase
            .getDatabase(context)!!
            .USerDao()
            ?.insert(book)
        val bookdb = UserDataBase
            .getDatabase(context)!!
            .USerDao()
            ?.getAllBook()
        if (bookdb != null) {
            for(i in bookdb){ Log.d("bookList", "${i.idx} | ${i.email}  | ${i.Username} | ${i.profile}") }
        }


    }
}





