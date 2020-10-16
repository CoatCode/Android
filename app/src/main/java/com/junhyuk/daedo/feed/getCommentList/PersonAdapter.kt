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
import com.junhyuk.daedo.R
import java.time.ZonedDateTime

//recyclerview adapter
class PersonAdapter(private val context: Context, private val personList : ArrayList<SchoolData>) : RecyclerView.Adapter<PersonAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.comment_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(personList[position])
        //recyclerview item 간격 조정 코드
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 300
        holder.itemView.requestLayout()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //recyclerview 에 올릴 프로토스 사진
        private val hisphoto = itemView.findViewById<ImageView>(R.id.his_face)
        //recyclerview 에 올릴 프로토스 이름
        private val hisName = itemView.findViewById<TextView>(R.id.his_name2)
        //recyclerview 에 올릴 프로토스 나이
        private val hisAge = itemView.findViewById<TextView>(R.id.his_age2)

        private val hisTime = itemView.findViewById<TextView>(R.id.write_time)

        fun bind (Person: SchoolData) {
            val GetTime = time()
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
            Glide.with(context)
                .load(Person.owner?.profile)
                .into(hisphoto)
            hisName?.text = Person.owner?.username
            hisAge?.text = Person.content
            Log.d("ct","ct: ${time.toLong()}")
            hisTime?.text =  GetTime.formatTimeString(year,month,day,hour,minute,second)


        }
    }
}