package com.junhyuk.daedo.main.bottomItem.post.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.post.adapter.PostImageAdapter
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {

    private lateinit var postImageAdapter: PostImageAdapter

    private val imageList = ArrayList<Uri>()

    private var hashTagList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        //엑션바 숨기기
        val actionBar = supportActionBar
        actionBar?.hide()

        addImageButton.setOnClickListener {
            val imageIntent = Intent() //구글 갤러리 접근 intent 변수

            if (imageList.size < 5){
                //구글 갤러리 접근
                imageIntent.type = "image/*"
                imageIntent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(imageIntent, 101)
            }else{
                Toast.makeText(this, "사진은 5개까지 올릴 수 있습니다.", Toast.LENGTH_LONG).show()
            }

        }

//        inputHashtag.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
////                checkHashTag(inputHashtag.toString())
////                //inputHashtag.isEnabled = hashTagList.size < 5
////                Log.d("hashTag", "tag: ${hashTagList.toArray()}")
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//
//        })

        postImageAdapter = PostImageAdapter(imageList, applicationContext)
        recyclerView.adapter = postImageAdapter
    }

    fun checkHashTag(hashTag: String){

        val hashTagArray: ArrayList<String> = hashTag.split("#") as ArrayList<String>

        hashTagList = hashTagArray

    }

    //갤러리에서 넘어온 이미지 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 이미지 파일이 넘어 왔을 경우
        if (requestCode == 101 && resultCode == RESULT_OK) {
            try {

                imageList.add(data?.data!!)
                postImageAdapter.notifyDataSetChanged()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //어떤 파일도 넘어오지 않았을 때
        else if (requestCode == 101 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진을 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}