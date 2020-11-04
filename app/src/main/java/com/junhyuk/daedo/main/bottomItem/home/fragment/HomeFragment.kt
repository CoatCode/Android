package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.home.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

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

        return view

    }
}