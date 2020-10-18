package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.home.workinRetrofit.SetUpRetrofit

class FeedFragment : Fragment() {

    //서버 통신
    private lateinit var setUpRetrofit: SetUpRetrofit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        setUpRetrofit = SetUpRetrofit()

        return view

    }
}