package com.junhyuk.daedo.main.bottomItem.profile

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.activity.MainActivity
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.ItemSize
import com.junhyuk.daedo.main.bottomItem.home.fragment.HomeFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GetProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val callUserId: Int = (activity as MainActivity).userId
        val callUserProfile: String? = (activity as MainActivity).userProfile
        val callUserName: String? = (activity as MainActivity).userName
        val callUserDescription: String? = (activity as MainActivity).userDescription
        val callUserFollower: Int = (activity as MainActivity).followers
        val callUserFollowing : Int = (activity as MainActivity).following
        val applicationBox = activity
        val getUserProfile = GetUserProfile()
        val picture = view.findViewById<ImageView>(R.id.user_profile_image)
        val postList = arrayListOf<UserProfileData>()
        lateinit var mAdapter: ProfileRecyclerViewAdapter

        Glide.with(requireContext())
            .load(callUserProfile)
            .transform(CenterCrop(), RoundedCorners(1000000000))
            .into(picture)
        view.profile_user_name?.text = callUserName
        view.user_profile_detail?.text = callUserDescription
        view.follower_count.text = callUserFollower.toString()
        view.following_count.text = callUserFollowing.toString()
        mAdapter = ProfileRecyclerViewAdapter(requireContext(), postList, view, callUserId) {
        }
        getUserProfile.getUserProfile(applicationBox!!.application,callUserId,mAdapter,postList)

        view.profile_post.setHasFixedSize(true)
        view.profile_post?.adapter = mAdapter

        return view
    }


}
