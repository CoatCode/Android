package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.tabs.TabLayout
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.bottomItem.home.adapter.PagerAdapter
import com.junhyuk.daedo.main.bottomItem.home.profile.GetUserProfile
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.viewPager.offscreenPageLimit = 3

        val feedFragment = FeedFragment()
        val followFeedFragment = FollowFeedFragment()
        val hotFeedFragment = HotFeedFragment()
        val applicationBox = activity
        val getUserProfile = GetUserProfile()
        var callUserId: Int = 0
        CoroutineScope(Dispatchers.IO).launch {
            callUserId = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.id

            Log.d("userId", "userId1 : $callUserId")
            withContext(Dispatchers.Main) {
                Log.d("userId", "userId : $callUserId")

                view?.my_profile?.setOnClickListener {
                    Log.d("test", "test")
                    getUserProfile.getUserProfile(applicationBox!!.application, callUserId)
                }
            }
        }
        var callUserString: String? = ""
        CoroutineScope(Dispatchers.IO).launch {
            callUserString = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.profile

            withContext(Dispatchers.Main) {
                Glide.with(requireContext())
                    .load(callUserString)
                    .transform(CenterCrop(), RoundedCorners(1000000000))
                    .into(my_profile)

            }
        }
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

        return view

    }
}