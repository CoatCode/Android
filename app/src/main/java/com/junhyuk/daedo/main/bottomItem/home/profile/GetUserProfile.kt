package com.junhyuk.daedo.main.bottomItem.home.profile

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.main.bottomItem.home.data.PostId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetUserProfile (){
    internal fun getUserProfile(
        getApplication: Application,
        userId : Int
    ) {

        (getApplication as DaedoApplication).retrofit.create(UserProfileInterface::class.java)
            .getUserProfile(userId)
            .enqueue(object : Callback<ArrayList<UserProfileData>> {
                override fun onResponse(
                    call: Call<ArrayList<UserProfileData>>,
                    response: Response<ArrayList<UserProfileData>>
                ) {
                    if (response.code() == 200) {
                        Log.d("responseBody","responseBody${response.body()}")
                    }
                    if (response.code() == 401) {
                        Log.d("401", "401" + response.errorBody())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserProfileData>>, t: Throwable) {
                    Log.d("fa", "fa")
                }
            })
    }
}