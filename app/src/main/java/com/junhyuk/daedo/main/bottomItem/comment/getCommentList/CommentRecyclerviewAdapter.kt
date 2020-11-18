package com.junhyuk.daedo.main.bottomItem.comment.getCommentList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.dataBase.userDatabase.UserDataBase
import com.junhyuk.daedo.main.activity.MainActivity
import com.junhyuk.daedo.main.bottomItem.comment.BottomSheetDialog
import com.junhyuk.daedo.main.bottomItem.comment.getCommentNetwork.CommentData
import com.junhyuk.daedo.main.bottomItem.home.data.FeedDetailData.Companion.feedData
import com.junhyuk.daedo.main.bottomItem.home.module.FeedTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//recyclerview adapter
class CommentRecyclerviewAdapter(
    private val mainActivity : MainActivity,
    private val context: Context, private val commentList: ArrayList<CommentData>, val view: View?,
    val itemClick: (CommentData) -> Unit
) : RecyclerView.Adapter<CommentRecyclerviewAdapter.Holder>() {

    //모듈 정의
    private val feedPostTime = FeedTime()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.comment_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(commentList[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //유저 프로필 사진
        private val userProfile = itemView.findViewById<ImageView>(R.id.user_profile)

        //댓글 작성자
        private val userName = itemView.findViewById<TextView>(R.id.user_name)

        //댓글 내용
        private val commentContent = itemView.findViewById<TextView>(R.id.comment_content)

        //댓글 작성 시간
        private val commentTime = itemView.findViewById<TextView>(R.id.comment_time)

        private val button = itemView.findViewById<ImageButton>(R.id.comment_option)
        var callUserId: Int? = 0
        fun bind(Comment: CommentData) {
            val adapter = CommentRecyclerviewAdapter(mainActivity,context, commentList, view, itemClick)
            //댓글 작성자 프로필 이미지를 넣어준다.
            Glide.with(context)
                .load(Comment.owner?.profile)
                .transform(CenterCrop(), RoundedCorners(1000000000))
                .into(userProfile)
            //댓글 내용
            commentContent?.text = Comment.content
            //뎃글 작성자
            userName?.text = Comment.owner?.username
            //댓글 작성 시간
            commentTime?.text = feedPostTime.calFeedTimeComment(Comment.created_at)
            userProfile.setOnClickListener {
                mainActivity.userId = Comment.owner!!.id
                mainActivity.userProfile = Comment.owner!!.profile
                mainActivity.userName = Comment.owner!!.username
                mainActivity.userDescription = Comment.owner!!.description
                mainActivity.followers = Comment.owner!!.followers
                mainActivity.following = Comment.owner!!.following
                val navController: NavController = Navigation.findNavController(it)
                navController.navigate(R.id.action_feedDetailFragment_to_getProfileFragment)
            }
            //댓글 상세 버튼 클릭 이벤트
            button.setOnClickListener {
                itemClick(Comment)
                val bottomSheet = BottomSheetDialog(Comment, view, adapter)
                BottomSheetDialog(Comment, view, adapter)
                //bottomSheetDialog 를 띄운다
                CoroutineScope(Dispatchers.IO).launch {
                    callUserId = UserDataBase.getDatabase(context)!!
                        .userDao()
                        ?.getAllUser()?.last()!!.id
                    if (callUserId == Comment.owner!!.id)
                    bottomSheet.show(
                        (context as AppCompatActivity).supportFragmentManager,
                        bottomSheet.tag
                    )
                }

            }
        }
    }
}