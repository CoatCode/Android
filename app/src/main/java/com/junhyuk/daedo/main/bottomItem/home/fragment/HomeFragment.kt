package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.provider.Contacts
import android.provider.Contacts.SettingsColumns.KEY
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.tabs.TabLayout
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.activity.MainActivity
import com.junhyuk.daedo.main.bottomItem.home.adapter.PagerAdapter
import com.junhyuk.daedo.main.bottomItem.profile.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {

    //뒤로가기
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        requireActivity().nav_view.visibility = View.VISIBLE

        view.viewPager.offscreenPageLimit = 3

        val feedFragment = FeedFragment()
        val followFeedFragment = FollowFeedFragment()
        val hotFeedFragment = HotFeedFragment()

        val pagerAdapter = PagerAdapter(
            activity?.supportFragmentManager!!,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        pagerAdapter.addItem(feedFragment)
        pagerAdapter.addItem(followFeedFragment)
        pagerAdapter.addItem(hotFeedFragment)

        val textArrayList = ArrayList<String>()

        textArrayList.add("전체")
        textArrayList.add("팔로우")
        textArrayList.add("인기")

        view.viewPager.adapter = pagerAdapter

        view.tabLayout.setupWithViewPager(view.viewPager)

        for(i in 0..2){
            val tab: TabLayout.Tab? = view.tabLayout.getTabAt(i)
            tab?.text = textArrayList[i]
        }
        var callUserId: Int? = 0
        var callUserProfile: String? = ""
        var callUserName: String? = ""
        var callUserDescription: String? = ""
        var callUserFollower: Int?
        var callUserFollowing : Int?
        CoroutineScope(Dispatchers.IO).launch {
            callUserId = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.id
            Log.d("id","id : $callUserId")
            callUserProfile = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.profile
            callUserName = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.Username
            Log.d("Username","UserName : $callUserName")
            callUserDescription = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.description
            callUserFollower = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.followers
            callUserFollowing = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.following
            withContext(Dispatchers.Main) {
                  Glide.with(requireContext())
                      .load(callUserProfile)
                      .transform(CenterCrop(), RoundedCorners(1000000000))
                      .into(my_profile)

                my_profile.setOnClickListener {
                    (activity as MainActivity).userId = callUserId!!
                    (activity as MainActivity).userProfile = callUserProfile.toString()
                    (activity as MainActivity).userDescription = callUserDescription.toString()
                    (activity as MainActivity).userName = callUserName.toString()
                    (activity as MainActivity).followers = callUserFollower!!.toInt()
                    (activity as MainActivity).following = callUserFollowing!!.toInt()
                    findNavController().navigate(R.id.action_navigation_home_to_getProfileFragment)
                }

                //view?.user_profile_detail?.text = callUserDescription
                //view?.follower_count?.text = callUserFollower.toString()
                //view?.following_count?.text = callUserFollowing.toString()
            }
        }
        return view
    }

    //뒤로가기
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().moveTaskToBack(true)
                requireActivity().finishAndRemoveTask()
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}