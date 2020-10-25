package com.junhyuk.daedo.feed.getCommentList

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.BottomSheetDialog
import com.junhyuk.daedo.feed.deleteComment.DeleteComment
import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import java.time.ZonedDateTime

//recyclerview adapter
class CommentRecyclerviewAdapter(private val context: Context, private val commentList : ArrayList<CommentData>, val view : View?,
                                 val itemClick: (CommentData) -> Unit) : RecyclerView.Adapter<CommentRecyclerviewAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.comment_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(commentList[position])
        //recyclerview item 간격 조정 코드
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = layoutParams.height
        holder.itemView.requestLayout()


    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //recyclerview 에 올릴 프로토스 사진
        private val userProfile = itemView.findViewById<ImageView>(R.id.user_profile)
        //댓글 작성자
        private val userName = itemView.findViewById<TextView>(R.id.user_name)
        //댓글 내용
        private val commentContent = itemView.findViewById<TextView>(R.id.comment_content)
        //댓글 작성 시간
        private val commentTime = itemView.findViewById<TextView>(R.id.comment_time)

        private val button = itemView.findViewById<ImageButton>(R.id.comment_option)

        fun bind (Comment: CommentData) {
            val activityBox = Activity()
            val getTime = GetCommentTime()
            val deleteComment = DeleteComment()
            val adapter = CommentRecyclerviewAdapter(context,commentList,view, itemClick)
            val year = ZonedDateTime.parse(Comment.created_at).year
            val month = ZonedDateTime.parse(Comment.created_at).monthValue
            val day = ZonedDateTime.parse(Comment.created_at).dayOfMonth
            val hour = ZonedDateTime.parse(Comment.created_at).hour
            val minute = ZonedDateTime.parse(Comment.created_at).minute
            val second = ZonedDateTime.parse(Comment.created_at).second
            val time = year + month + day + hour + minute + second
            Log.d("time","time:$time")
            Log.d("year","year : $year")
            Log.d("month","month : $month")
            Log.d("day","day : $day")
            Log.d("hour","hour : $hour")
            Log.d("minute","minute : $minute")
            Log.d("second","second : $second")
            Log.d("jsonTime","jsonTime : ${Comment.created_at}")
            Log.d("content","content : ${Comment.content}")
            Log.d("comment_id","comment_id : ${Comment.comment_id}")
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
            commentTime?.text =  getTime.formatTimeString(year,month,day,hour,minute,second)
            //댓글 상세 버튼 클릭 이벤트
            button.setOnClickListener{
                itemClick(Comment)
                val bottomSheet = BottomSheetDialog(Comment,view,adapter)
                BottomSheetDialog(Comment,view,adapter)
                //bottomSheetDialog 를 띄운다
                bottomSheet.show((context as AppCompatActivity).supportFragmentManager,bottomSheet.tag)

            }
        }
    }
}