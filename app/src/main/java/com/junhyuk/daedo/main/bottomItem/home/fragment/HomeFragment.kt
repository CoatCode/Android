package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.tabs.TabLayout
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.bottomItem.home.adapter.PagerAdapter
import com.junhyuk.daedo.main.bottomItem.home.profile.GetProfileFragment
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
        var callUserId: Int = 0
        var callUserProfile: String? = ""
        var callUserName : String? = ""
        view.my_profile.setOnClickListener {
            Log.d("exam","exam")

            Log.d("exam","exam")
        }

        CoroutineScope(Dispatchers.IO).launch {
            callUserId = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.id
            callUserProfile = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.profile
            callUserName = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.Username
            withContext(Dispatchers.Main) {
                Glide.with(requireContext())
                    .load(callUserProfile)
                    .transform(CenterCrop(), RoundedCorners(1000000000))
                    .into(my_profile)
            }
            withContext(Dispatchers.Main) {
                view?.my_profile?.setOnClickListener {
                    Log.d("test", "test")
                    Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_getProfileFragment)

                    //  GetProfileFragment(callUserProfile,callUserName)
                    Log.d("why","why")
                }
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