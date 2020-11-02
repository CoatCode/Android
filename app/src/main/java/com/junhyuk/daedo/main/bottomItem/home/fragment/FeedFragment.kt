package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.home.adapter.FeedAdapter
import com.junhyuk.daedo.main.bottomItem.home.model.FeedViewModel
import com.junhyuk.daedo.main.bottomItem.home.profile.GetUserProfile
import kotlinx.android.synthetic.main.activity_email_login.*
import kotlinx.android.synthetic.main.fragment_feed.view.*
import kotlinx.android.synthetic.main.fragment_feed_detail_item.view.*


class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        view.feedRecyclerView.setHasFixedSize(true)

        val feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        val feedAdapter = FeedAdapter(activity?.applicationContext!!, activity!!)

        feedViewModel.feedPagedList.observe(viewLifecycleOwner, Observer {
            feedAdapter.submitList(it)
        })

        view.feedRecyclerView.adapter = feedAdapter

        return view

    }
}