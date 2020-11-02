package com.junhyuk.daedo.main.bottomItem.home.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase


class GetProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val callUserInformation = UserDataBase.getDatabase(requireContext())
            ?.userDao()
            ?.getAllUser()
        if (callUserInformation != null) {
            for(i in callUserInformation){ Log.d("UserDB", "${i.idx} | ${i.doNotTouch} | ${i.id} | ${i.Username} " +
                    "| ${i.email} | ${i.profile} | ${i.followers} | ${i.following}") }
        }

        val activityBox = activity
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        return view
    }
}
