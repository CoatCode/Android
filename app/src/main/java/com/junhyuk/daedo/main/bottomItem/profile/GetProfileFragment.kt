package com.junhyuk.daedo.main.bottomItem.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.activity.MainActivity
import kotlinx.android.synthetic.main.activity_email_login.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class GetProfileFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
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
        view.back_button_feed.setOnClickListener {
            findNavController().navigate(R.id.action_getProfileFragment_to_navigation_home)
        }

        view.profile_post.setHasFixedSize(true)
        view.profile_post?.adapter = mAdapter

        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_getProfileFragment_to_navigation_home)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}
