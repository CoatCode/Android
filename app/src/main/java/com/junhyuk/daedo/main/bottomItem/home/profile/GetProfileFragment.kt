package com.junhyuk.daedo.main.bottomItem.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.ItemSize
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import kotlinx.android.synthetic.main.fragment_comment.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GetProfileFragment : Fragment() {
    private lateinit var mAdapter: ProfileRecyclerViewAdapter
    var commentList = arrayListOf<UserProfileData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var callUserId : Int = 0
        var callUserProfile : String? = ""
        var callUserName : String? = ""
        var callUserDescription : String? = ""
        mAdapter = ProfileRecyclerViewAdapter(requireContext(), commentList,view) {
        }
        val getUserProfile = GetUserProfile()
        val applicationBox = activity
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
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
            callUserDescription = UserDataBase.getDatabase(requireContext())!!
                .userDao()
                ?.getAllUser()?.last()!!.description
            withContext(Dispatchers.Main) {
              /*  Glide.with(requireContext())
                    .load(callUserProfile)
                    .transform(CenterCrop(), RoundedCorners(1000000000))
                    .into(my_profile)*/
                view?.profile_user_name?.text = callUserName
                view?.user_profile_detail?.text = callUserDescription
                val lm = LinearLayoutManager(context)
                view.comment_recycler_view.layoutManager = lm
                view.comment_recycler_view.addItemDecoration(ItemSize(100))
                getUserProfile.getUserProfile(applicationBox!!.application, callUserId)
            }
        }
        return view
    }
}


