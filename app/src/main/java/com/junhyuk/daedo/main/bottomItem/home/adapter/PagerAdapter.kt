
package com.junhyuk.daedo.main.bottomItem.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


open class PagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    private val items: ArrayList<Fragment> = ArrayList() //tab 의 view pager 에 들어갈 프레그먼트를 저장하는 리스트

    fun addItem(item: Fragment) {
        items.add(item) //tab 의 view pager 에 들어갈 프레그먼트를 추가
    }

    override fun getItem(position: Int): Fragment {
        return items[position] //position 에 들어온 값에 따라서 맞는 프레그먼트를 반환
    }

    override fun getCount(): Int {
        return items.size //tab 의 갯수를 item 의 크기 만큼  반환
    }
}