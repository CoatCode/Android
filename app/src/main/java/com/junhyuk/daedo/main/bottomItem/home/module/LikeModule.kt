package com.junhyuk.daedo.main.bottomItem.home.module

import android.content.Context
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.junhyuk.daedo.R
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.main.bottomItem.home.adapter.FeedAdapter
import com.junhyuk.daedo.main.bottomItem.home.data.LikeResponse
import com.junhyuk.daedo.main.bottomItem.home.workinRetrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_feed_detail_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeModule {

    fun likeModule(
        context: Context,
        position: Int,
        view: View
    ) {
        val retrofitClient = RetrofitClient()
        val token = EmailLoginBody.instance?.access_token

        retrofitClient
            .getInstance()
            ?.getApi()
            ?.requestLikeBool("Bearer $token", position)
            ?.enqueue(object : Callback<LikeResponse> {
                override fun onResponse(
                    call: Call<LikeResponse>,
                    response: Response<LikeResponse>
                ) {
                    when (response.code()) {
                        200 -> {
                            Glide.with(context)
                                .load(R.drawable.good_color)
                                .into(view.heart)
                        }

                        400 -> {
                            Glide.with(context)
                                .load(R.drawable.good)
                                .into(view.heart)
                        }
                    }
                }

                override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "네트워크 상태를 제대로 확인 해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

}