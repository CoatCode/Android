package com.junhyuk.daedo.main.bottomItem.post.activity

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.junhyuk.daedo.R
import com.junhyuk.daedo.main.bottomItem.post.adapter.PostImageAdapter
import com.junhyuk.daedo.main.bottomItem.post.workingRetrofit.SetupRetrofit
import kotlinx.android.synthetic.main.activity_post.*


class PostActivity : AppCompatActivity() {

    //recyclerview
    private lateinit var postImageAdapter: PostImageAdapter
    private val imageUriList = ArrayList<Uri>()
    private val imagePathList = ArrayList<String>()

    //서버 통신에 필요한 요소
    private var postTitleStr = ""
    private var postContentStr: String = ""
    private var hashTag: String = ""
    private var hashTagSize = ArrayList<String>()
    private var imageName = ""

    //서버 통신(retrofit)`
    private val setupRetrofit = SetupRetrofit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        //엑션바 숨기기
        val actionBar = supportActionBar
        actionBar?.hide()

        //화면에 이미지 표시
        Glide.with(application)
            .load(R.drawable.add)
            .thumbnail(Glide.with(application).load(R.raw.loading))
            .override(100, 100)
            .transform(CenterCrop(), RoundedCorners(20))
            .into(addImage)

        addImageButton.setOnClickListener {
            val imageIntent = Intent() //구글 갤러리 접근 intent 변수

            if (imagePathList.size < 5) {
                //구글 갤러리 접근
                imageIntent.type = "image/*"
                imageIntent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(imageIntent, 101)
            } else {
                Toast.makeText(this, "사진은 5개까지 올릴 수 있습니다.", Toast.LENGTH_SHORT).show()
            }

        }

        uploadButton.setOnClickListener {
            if (postTitle.text.isEmpty() || postContent.text.isEmpty() || imageUriList.size == 0) {
                Toast.makeText(this, "제목이나 내용, 사진등을 다시 확인해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                postTitleStr = postTitle.text.toString()
                postContentStr = postContent.text.toString()

                setupRetrofit.setUpRetrofit(
                    application,
                    this,
                    imagePathList,
                    imageName,
                    postTitleStr,
                    postContentStr,
                    hashTag
                )
            }
        }

        xButton.setOnClickListener {
            finish()
        }

        inputHashtag.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (inputHashtag.text.contains("#")) {
                    hashTag = inputHashtag.text.toString()
                    setHashTag(inputHashtag.text.toString())
                } else {
                    Toast.makeText(this@PostActivity, "해시태그는 #이 포함되어 있어야 합니다.", Toast.LENGTH_SHORT)
                        .show()
                }

                if (inputHashtag.text.toString().isEmpty()) {
                    hashTag = ""
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        editHashtag.setOnClickListener {
            inputHashtag.isEnabled = true
            editHashtag.visibility = View.INVISIBLE
            editHashtag.isEnabled = false
        }

        postImageAdapter = PostImageAdapter(imageUriList, applicationContext)
        recyclerView.adapter = postImageAdapter
    }

    //해시태그
    private fun setHashTag(hashTag: String) {
        val hashList: ArrayList<String> = hashTag.split("#") as ArrayList<String>
        hashList.removeAt(0)

        if (hashList.size > 5) {
            Toast.makeText(this@PostActivity, "해시태그는 5개까지 추가 할 수 있습니다", Toast.LENGTH_SHORT).show()
            inputHashtag.isEnabled = false

            val hashTagText: StringBuilder = java.lang.StringBuilder()

            for (i in 0 until hashTagSize.size) {
                hashTagText.append("#${hashList[i]}")
                inputHashtag.setText(hashTagText)
                editHashtag.visibility = View.VISIBLE
                editHashtag.isEnabled = true
            }
        } else {
            hashTagSize.clear()
            hashTagSize = hashList
            editHashtag.isEnabled = false
        }
        Log.d("HashTag", "hashTag: $hashTagSize")
    }

    //갤러리에서 넘어온 이미지 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val returnUri: Uri
        val returnCursor: Cursor?

        // 이미지 파일이 넘어 왔을 경우
        if (requestCode == 101 && resultCode == RESULT_OK) {
            try {

                //이미지 정보
                returnUri = data?.data!!

                imageUriList.add(returnUri)
                postImageAdapter.notifyDataSetChanged()

                imagePathList.add(getRealPathFromURI(returnUri).toString())
                Log.d("image", "image: ${returnUri.path}")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //어떤 파일도 넘어오지 않았을 때
        else if (requestCode == 101 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진을 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // 실제 경로 찾기
    private fun getRealPathFromURI(contentUri: Uri): String? {
        if (contentUri.path!!.startsWith("/storage")) {
            return contentUri.path
        }
        val id = DocumentsContract.getDocumentId(contentUri).split(":".toRegex()).toTypedArray()[1]
        val columns = arrayOf(MediaStore.Files.FileColumns.DATA)
        val selection = MediaStore.Files.FileColumns._ID + " = " + id
        val cursor = contentResolver.query(
            MediaStore.Files.getContentUri("external"),
            columns,
            selection,
            null,
            null
        )
        try {
            val columnIndex = cursor!!.getColumnIndex(columns[0])
            if (cursor.moveToFirst()) {

                Log.d("image", "image: ${cursor.getString(columnIndex)}")
                return cursor.getString(columnIndex)
            }
        } finally {
            cursor!!.close()
        }


        return null
    }



}