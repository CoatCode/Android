package com.junhyuk.daedo.main.bottomItem.home.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.activity.MainActivity
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.CommentRecyclerviewAdapter
import com.junhyuk.daedo.main.bottomItem.comment.getCommentList.GetCommentList
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import com.junhyuk.daedo.main.bottomItem.comment.writeComment.SendWriteComment
import com.junhyuk.daedo.main.bottomItem.home.adapter.ImageSliderAdapter
import com.junhyuk.daedo.main.bottomItem.home.data.FeedDetailData
import com.junhyuk.daedo.main.bottomItem.home.data.FeedDetailData.Companion.feedData
import com.junhyuk.daedo.main.bottomItem.home.module.LikeClickModule
import com.junhyuk.daedo.main.bottomItem.home.module.LikeModule
import com.junhyuk.daedo.main.bottomItem.profile.GetUserProfile
import com.junhyuk.daedo.main.bottomItem.profile.ProfileRecyclerViewAdapter
import com.junhyuk.daedo.main.bottomItem.profile.UserProfileData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_feed_detail_item.edit_comment
import kotlinx.android.synthetic.main.fragment_feed_detail_item.view.*
import kotlinx.android.synthetic.main.fragment_feed_detail_item.view.write_comment_button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder


class FeedDetailFragment : Fragment() {

    //뒤로가기
    private lateinit var callback: OnBackPressedCallback

    //댓글
    private var commentList = arrayListOf<CommentData>()
    private val sendComment = SendWriteComment()
    private val getComment = GetCommentList()
    private lateinit var commentAdapter: CommentRecyclerviewAdapter
    private var comment: String = ""

    //모듈
    private val likeClickModule = LikeClickModule()
    private val likeModule = LikeModule()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed_detail_item, container, false)

        requireActivity().nav_view.visibility = View.VISIBLE

        commentAdapter = CommentRecyclerviewAdapter((activity as MainActivity),requireContext(), commentList,view){}

        Glide.with(requireActivity().applicationContext)
            .load(FeedDetailData.feedData.owner.profile)
            .override(100)
            .transform(CenterCrop(), RoundedCorners(1000000))
            .into(view.profile)
        view.profile.setOnClickListener {
            (activity as MainActivity).userId = FeedDetailData.feedData.id
            findNavController().navigate(R.id.action_navigation_home_to_getProfileFragment)
        }

        view.name.text = FeedDetailData.feedData.owner.username
        view.date.text = FeedDetailData.date

        val adapter = ImageSliderAdapter(
            FeedDetailData.feedData.image_urls,
            requireActivity().applicationContext
        )

        view.feedImage.adapter = adapter

        view.imageSlider.setViewPager(view.feedImage)

        view.title.text = FeedDetailData.feedData.title
        view.contentText.text = FeedDetailData.feedData.content
        val stringBuilder = StringBuilder("")
        if (!(FeedDetailData.feedData.tag.isNullOrEmpty())) {
            FeedDetailData.feedData.tag.forEach {
                stringBuilder.append(it)
            }
        }
        view.hashTag.text = stringBuilder

        val likeCountString = FeedDetailData.feedData.like_count.toString() + " likes"
        val viewCountString = FeedDetailData.feedData.view_count.toString() + " view"
        val commentCountString = FeedDetailData.feedData.comment_count.toString() + " comments"

        view.likeCount.text = likeCountString
        view.viewerCount.text = viewCountString
        view.commentCount.text = commentCountString

        view.heart.setOnClickListener {
            likeClickModule.likeClickModule(requireContext(), FeedDetailData.feedData, view)
        }

        likeModule.likeModule(requireContext(), FeedDetailData.feedData.id, view)

        //댓글 목록 받아오는 클래스로 값 전달
        getComment.getCommentList(
            requireActivity().application,
            commentAdapter,
            commentList
        )

        view.comment_recycler_view.adapter = commentAdapter

        view.write_comment_button.setOnClickListener {

            //로딩 다이얼로그
            val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
            sweetAlertDialog
                .setTitleText("로딩 중")
                .setCancelable(false)
            sweetAlertDialog.show()

            //작성한 댓글 변수에 저장
            comment = this.edit_comment.text.toString()

            //작성한 댓글 서버통신 클래스로 값 전달
            sendComment.sendComment(comment, requireActivity().application)

            view.edit_comment.text = null

            getComment.getCommentList(
                requireActivity().application,
                commentAdapter,
                commentList
            )

            sweetAlertDialog.dismiss()

            view.parentLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorMainBackground))

            commentAdapter.notifyDataSetChanged()
        }
                view?.profile?.setOnClickListener {
                    Log.d("test", "test")
                    (activity as MainActivity).userId = feedData.owner.id
                    (activity as MainActivity).userProfile = feedData.owner.profile
                    (activity as MainActivity).userName = feedData.owner.username
                    (activity as MainActivity).userDescription = feedData.owner.description
                    (activity as MainActivity).followers = feedData.owner.followers
                    (activity as MainActivity).following = feedData.owner.following
                    val navController: NavController = Navigation.findNavController(it)
                    navController.navigate(R.id.action_feedDetailFragment_to_getProfileFragment)
                    //getUserProfile.getUserProfile(requireActivity().application, callUserInformation,mAdapter,postList)
                    //findNavController().navigate(R.id.action_feedDetailFragment_to_getProfileFragment)
                }




        return view
    }

    //뒤로가기
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_feedDetailFragment_to_navigation_home)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}