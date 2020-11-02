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
import kotlinx.android.synthetic.main.fragment_home.view.*


class ImageSliderItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.viewpager_image, container, false)



        return view

    }
}