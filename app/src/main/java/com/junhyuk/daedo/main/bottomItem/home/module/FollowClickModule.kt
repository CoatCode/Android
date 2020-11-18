package com.junhyuk.daedo.main.bottomItem.home.module

import android.content.Context
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.junhyuk.daedo.R
import com.junhyuk.daedo.emailLogin.server.EmailLoginBody
import com.junhyuk.daedo.main.bottomItem.follow.FollowResponse
import com.junhyuk.daedo.main.bottomItem.home.adapter.FeedAdapter
import com.junhyuk.daedo.main.bottomItem.home.data.FeedData
import com.junhyuk.daedo.main.bottomItem.home.data.LikeResponse
import com.junhyuk.daedo.main.bottomItem.home.workinRetrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_feed_detail_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowClickModule {

    //recycler Adapter 용
    fun likeClickModule(
        context: Context,
        feedData: FeedData,
        holder: FeedAdapter.Holder
    ) {
        val retrofitClient = RetrofitClient()
        val token = EmailLoginBody.instance?.access_token

        retrofitClient
            .getInstance()
            ?.getFollow()
            ?.requestFollowBool("Bearer $token", feedData.id)
            ?.enqueue(object : Callback<FollowResponse> {
                override fun onResponse(
                    call: Call<FollowResponse>,
                    response: Response<FollowResponse>
                ) {
                    when (response.code()) {
                        200 -> {
                            retrofitClient
                                .getInstance()
                                ?.getFollow()
                                ?.requestFollowCancel("Bearer $token", feedData.id)
                                ?.enqueue(object : Callback<FollowResponse> {
                                    override fun onResponse(
                                        call: Call<FollowResponse>,
                                        response: Response<FollowResponse>
                                    ) {
                                        when (response.code()) {
                                            200 -> {
                                                Toast.makeText(
                                                    context,
                                                    "해당 게시물에 좋아요를 취소했습니다.",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                feedData.like_count -= 1
                                                holder.heartCount.text = feedData.like_count.toString()

                                                Glide.with(context)
                                                    .load(R.drawable.good)
                                                    .into(holder.heartButton)
                                            }
                                            400 -> {
                                                Toast.makeText(
                                                    context,
                                                    "네트워크 상태를 확인해 주세요.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<FollowResponse>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "네트워크 상태를 제대로 확인 해주세요.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }

                        400 -> {
                            retrofitClient
                                .getInstance()
                                ?.getFollow()
                                ?.requestFollow("Bearer $token", feedData.id)
                                ?.enqueue(object : Callback<FollowResponse> {
                                    override fun onResponse(
                                        call: Call<FollowResponse>,
                                        response: Response<FollowResponse>
                                    ) {
                                        when (response.code()) {
                                            200 -> {
                                                Toast.makeText(
                                                    context,
                                                    "해당 게시물에 좋아요를 눌렀습니다.",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                feedData.like_count += 1
                                                holder.heartCount.text = feedData.like_count.toString()

                                                Glide.with(context)
                                                    .load(R.drawable.good_color)
                                                    .into(holder.heartButton)
                                            }
                                            400 -> {
                                                Toast.makeText(
                                                    context,
                                                    "네트워크 상태를 확인해 주세요.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<FollowResponse>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "네트워크 상태를 제대로 확인 해주세요.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }
                    }
                }

                override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "네트워크 상태를 제대로 확인 해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

    //fragment, activity 용
    fun likeClickModule(
        context: Context,
        feedData: FeedData,
        view: View
    ) {
        val retrofitClient = RetrofitClient()
        val token = EmailLoginBody.instance?.access_token

        retrofitClient
            .getInstance()
            ?.getFollow()
            ?.requestFollowBool("Bearer $token", feedData.id)
            ?.enqueue(object : Callback<FollowResponse> {
                override fun onResponse(
                    call: Call<FollowResponse>,
                    response: Response<FollowResponse>
                ) {
                    when (response.code()) {
                        200 -> {
                            retrofitClient
                                .getInstance()
                                ?.getFollow()
                                ?.requestFollowCancel("Bearer $token", feedData.id)
                                ?.enqueue(object : Callback<FollowResponse> {
                                    override fun onResponse(
                                        call: Call<FollowResponse>,
                                        response: Response<FollowResponse>
                                    ) {
                                        when (response.code()) {
                                            200 -> {
                                                Toast.makeText(
                                                    context,
                                                    "해당 게시물에 좋아요를 취소했습니다.",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                feedData.like_count -= 1
                                                val likeCountString = feedData.like_count.toString() + " likes"
                                                view.likeCount.text = likeCountString

                                                Glide.with(context)
                                                    .load(R.drawable.good)
                                                    .into(view.heart)
                                            }
                                            400 -> {
                                                Toast.makeText(
                                                    context,
                                                    "네트워크 상태를 확인해 주세요.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<FollowResponse>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "네트워크 상태를 제대로 확인 해주세요.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }

                        400 -> {
                            retrofitClient
                                .getInstance()
                                ?.getFollow()
                                ?.requestFollow("Bearer $token", feedData.id)
                                ?.enqueue(object : Callback<FollowResponse> {
                                    override fun onResponse(
                                        call: Call<FollowResponse>,
                                        response: Response<FollowResponse>
                                    ) {
                                        when (response.code()) {
                                            200 -> {
                                                Toast.makeText(
                                                    context,
                                                    "해당 게시물에 좋아요를 눌렀습니다.",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                feedData.like_count += 1
                                                val likeCountString = feedData.like_count.toString() + " likes"
                                                view.likeCount.text = likeCountString

                                                Glide.with(context)
                                                    .load(R.drawable.good_color)
                                                    .into(view.heart)
                                            }
                                            400 -> {
                                                Toast.makeText(
                                                    context,
                                                    "네트워크 상태를 확인해 주세요.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<FollowResponse>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "네트워크 상태를 제대로 확인 해주세요.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }
                    }
                }

                override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "네트워크 상태를 제대로 확인 해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

}