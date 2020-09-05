package com.junhyuk.daedo.EmailLogin.UserDataActivity

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
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
        ?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200){
                    val addBook = AddBook(context)
                    addBook.start()

                }
                Log.d("stoken","token="+EmailLoginBody.instance?.access_token)
                Log.d("stoken","stoken$token")
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("ftoken","token="+EmailLoginBody.instance?.access_token)
            }

        })

  }
}
class AddBook(val context: Context) : Thread() {
    override fun run() {
        val book = User(1, "공간이 만든 공간", "유현준", "교양 인문학", "")
        UserDataBase
            .getDatabase(context)!!
            .USerDao()
            ?.insert(book)
        
        val bookdb = UserDataBase
            .getDatabase(context)!!
            .USerDao()
            ?.getAllBook()


        if (bookdb != null) {
            for(i in bookdb){ Log.d("bookList", "${i.idx} | ${i.email} ${i.Username} | ${i.profile}") }
        }


    }
}





