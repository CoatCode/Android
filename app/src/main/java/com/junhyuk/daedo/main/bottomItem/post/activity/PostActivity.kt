package com.junhyuk.daedo.main.bottomItem.post.activity

import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
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
import com.junhyuk.daedo.signUp.rotateImage.RotateImage
import kotlinx.android.synthetic.main.activity_post.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream


class PostActivity : AppCompatActivity() {

    //이미지 관련 상수

    private val profileImagePick = 69

    //recyclerview
    private lateinit var postImageAdapter: PostImageAdapter
    private val imageList = ArrayList<Bitmap>()
    private val imageMultipart = ArrayList<RequestBody>()
    private val imageNameList = ArrayList<String>()

    //서버 통신에 필요한 요소
    private var postTitleStr = ""
    private var postContentStr: String = ""
    private var hashTag: String = ""
    private var hashTagSize = ArrayList<String>()
    private val rotateImageClass = RotateImage() //이미지 회전


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

            if (imageMultipart.size < 5) {
                //구글 갤러리 접근
                imageIntent.type = "image/*"
                imageIntent.action = Intent.ACTION_GET_CONTENT
                if(imageIntent.resolveActivity(packageManager) != null){
                    startActivityForResult(imageIntent, profileImagePick)
                }
            } else {
                Toast.makeText(this, "사진은 5개까지 올릴 수 있습니다.", Toast.LENGTH_SHORT).show()
            }

        }

        uploadButton.setOnClickListener {
            if (postTitle.text.isEmpty() || postContent.text.isEmpty() || imageList.size == 0) {
                Toast.makeText(this, "제목이나 내용, 사진등을 다시 확인해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                postTitleStr = postTitle.text.toString()
                postContentStr = postContent.text.toString()

                setupRetrofit.setUpRetrofit(
                    application,
                    this,
                    postTitleStr,
                    postContentStr,
                    hashTag,
                    imageMultipart,
                    imageNameList
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

        postImageAdapter = PostImageAdapter(imageList, applicationContext)
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

        if(resultCode == RESULT_OK){
            when(requestCode){
                profileImagePick -> {

                    //이미지 정보
                    returnUri = data?.data!!//이미지 커서

                    //이미지 파일 받아오기
                    val inputStream = contentResolver.openInputStream(returnUri) //input 스트림
                    var bm: Bitmap = BitmapFactory.decodeStream(inputStream) //비트맵 변환
                    bm = rotateImageClass.rotateImage(data.data!!, bm, contentResolver) //이미지 회전
                    val bos = ByteArrayOutputStream()
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                    imageMultipart.add(RequestBody.create(MultipartBody.FORM, bos.toByteArray()))

                    inputStream?.close()

                    imageList.add(bm)
                    postImageAdapter.notifyDataSetChanged()

                    returnCursor = contentResolver.query(returnUri, null, null, null, null)

                    //이미지 이름
                    val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    returnCursor.moveToFirst()
                    imageNameList.add(returnCursor.getString(nameIndex))

                    returnCursor.close()

                }

                else -> {
                    Toast.makeText(this, "이미지를 제대로 가져오지 못하였습니다.", Toast.LENGTH_LONG).show()
                }

            }
        }

    }
}