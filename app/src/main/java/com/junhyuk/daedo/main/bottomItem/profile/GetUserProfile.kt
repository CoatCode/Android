package com.junhyuk.daedo.main.bottomItem.profile

import android.app.Application
import android.util.Log
import com.junhyuk.daedo.application.DaedoApplication
import com.junhyuk.daedo.main.bottomItem.home.data.FeedDetailData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetUserProfile {
    var profilePostList = arrayListOf<UserProfileData>(
    )

    internal fun getUserProfile(
        getApplication: Application,
        userId: Int,
        pAdapter: ProfileRecyclerViewAdapter,
        postList: ArrayList<UserProfileData>
    ) {
        (getApplication as DaedoApplication).retrofit.create(UserProfileInterface::class.java)
            .getUserProfile(userId)
            .enqueue(object : Callback<ArrayList<UserProfileData>> {
                override fun onResponse(
                    call: Call<ArrayList<UserProfileData>>,
                    response: Response<ArrayList<UserProfileData>>
                ) {

                    if (response.code() == 200) {
                        profilePostList = response.body()!!

                        //postList.clear()

                        postList.addAll(profilePostList)
                        pAdapter.notifyDataSetChanged()
                        UserProfileData.instance = profilePostList;

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
