package com.junhyuk.daedo.feed.getCommentList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.junhyuk.daedo.R

//recyclerview adapter
class PersonAdapter(private val context: Context,private val PersonList : ArrayList<CommentData>) : RecyclerView.Adapter<PersonAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.comment_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return PersonList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(PersonList[position], context)
        //recyclerview item 간격 조정 코드
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 300
        holder.itemView.requestLayout()
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        //recyclerview 에 올릴 프로토스 사진
        private val hisPhoto = itemView?.findViewById<ImageView>(R.id.his_face)
        //recyclerview 에 올릴 프로토스 이름
        private val hisName = itemView?.findViewById<TextView>(R.id.his_name)
        //recyclerview 에 올릴 프로토스 나이
        private val hisAge = itemView?.findViewById<TextView>(R.id.his_age)

        fun bind (Person: CommentData, context: Context) {
            if (Person.photo != "") {
                val resourceId = context.resources.getIdentifier(Person.photo, "drawable", context.packageName)
                hisPhoto?.setImageResource(resourceId)
            } else {
                hisPhoto?.setImageResource(R.mipmap.ic_launcher)
            }
            hisName?.text = Person.name
            hisAge?.text = Person.comment
        }
    }






}