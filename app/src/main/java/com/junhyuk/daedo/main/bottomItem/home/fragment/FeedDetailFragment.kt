package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDataHandler.AddDatabase
import com.junhyuk.daedo.dataBase.userDataHandler.UserInformation
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentFragment
import com.junhyuk.daedo.main.bottomItem.home.profile.GetUserProfile
import kotlinx.android.synthetic.main.fragment_feed_detail_item.view.*
import kotlinx.coroutines.*


class FeedDetailFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val applicationBox = activity
        val getUserProfile = GetUserProfile()
        val view = inflater.inflate(R.layout.fragment_feed_detail_item, container, false)

        var callUserInformation : Int = 0
        CoroutineScope(Dispatchers.IO).launch{
            callUserInformation = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.id

            Log.d("userId","userId1 : $callUserInformation")
            withContext(Dispatchers.Main){
                Log.d("userId","userId : $callUserInformation")

                view?.profile?.setOnClickListener {
                    Log.d("test","test")
                    getUserProfile.getUserProfile(applicationBox!!.application,callUserInformation)
                }
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.add(R.id.commentFragment, CommentFragment())!!.commit()

            }
        }

        return view

    }


}