package com.junhyuk.daedo.feed.getCommentList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.feed.getCommentNetwork.CommentData
import java.time.ZonedDateTime

//recyclerview adapter
class PersonAdapter(private val context: Context, private val personList : ArrayList<CommentData>) : RecyclerView.Adapter<PersonAdapter.Holder>(){

    var index = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.linearlayout_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(personList[position])
        //recyclerview item 간격 조정 코드
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = index * 300
        holder.itemView.requestLayout()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //recyclerview 에 올릴 프로토스 사진
        private val userProfile = itemView.findViewById<ImageView>(R.id.linear_imageView)
        //댓글 작성자
        private val userName = itemView.findViewById<TextView>(R.id.linear_content)
        //댓글 내용
        private val commentContent = itemView.findViewById<TextView>(R.id.linear_username)
        //댓글 작성 시간
        private val commentTime = itemView.findViewById<TextView>(R.id.comment_time)

        fun bind (Person: CommentData) {
            val getTime = GetCommentTime()
            val year = ZonedDateTime.parse(Person.created_at).year
            val month = ZonedDateTime.parse(Person.created_at).monthValue
            val day = ZonedDateTime.parse(Person.created_at).dayOfMonth
            val hour = ZonedDateTime.parse(Person.created_at).hour
            val minute = ZonedDateTime.parse(Person.created_at).minute
            val second = ZonedDateTime.parse(Person.created_at).second
            val time = year + month + day + hour + minute + second
            Log.d("time","time:$time")
            Log.d("year","year : $year")
            Log.d("month","month : $month")
            Log.d("day","day : $day")
            Log.d("hour","hour : $hour")
            Log.d("minute","minute : $minute")
            Log.d("second","second : $second")
            Log.d("jsonTime","jsonTime : ${Person.created_at}")
            Log.d("content","content : ")
            Glide.with(context)
                .load(Person.owner?.profile)
                .transform(CenterCrop(), RoundedCorners(1000000000))
                .into(userProfile)
            commentContent?.text = Person.owner?.username

            val stringBuffer = StringBuffer()
            stringBuffer.append(Person.content)

           /* for(i in 1..Person.content.length){
                if(i % 14 == 0){
                    stringBuffer.insert(i, "\n")
                    index++
                }
            }*/
            userName?.text = stringBuffer
            Log.d("ct","ct: ${time.toLong()}")
           // commentTime?.text =  getTime.formatTimeString(year,month,day,hour,minute,second)
            Log.d("height","data: $stringBuffer")
            Log.d("height","line: $index")

        }
    }
}