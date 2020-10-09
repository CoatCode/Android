package com.junhyuk.daedo.feed.postDetail

/*
class DetailFragment : Fragment(){
    private var personList = arrayListOf<GetCommentArray>(
        //recyclerview 에 담길 값
        GetCommentArray("테사다르")

    )
    private lateinit var homeViewModel: HomeViewModel
    private var comment: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val sendComment = SendWriteComment()

        val context: FragmentActivity? = activity
        //댓글 작성 버튼 누를 시 SendComment 클래스 호출하고 입력받은 댓글을 넘겨준다
        root.write_comment?.setOnClickListener {
            comment = this.edit_comment.text.toString()
            Log.d("test","test$comment")
            sendComment.sendComment(comment, context!!.application)

        }

        return root
    }
}*/