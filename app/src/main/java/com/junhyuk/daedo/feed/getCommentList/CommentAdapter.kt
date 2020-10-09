package com.junhyuk.daedo.feed.getCommentList

/*
//recyclerview adapter
class CommentAdapter(private val context: Context, private val PersonList : ArrayList<GetCommentArray>) : RecyclerView.Adapter<CommentAdapter.Holder>(){
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

        fun bind (Comment: GetCommentArray, context: Context) {
            if (Comment.photo != "") {
                val resourceId = context.resources.getIdentifier(Comment.photo, "drawable", context.packageName)
                hisPhoto?.setImageResource(resourceId)
            } else {
                hisPhoto?.setImageResource(R.mipmap.ic_launcher)
            }
            hisName?.text = Comment.test
            hisAge?.text = Comment.age
        }
    }






}
*/
