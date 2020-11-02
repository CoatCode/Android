
package com.junhyuk.daedo.main.bottomItem.home.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import com.junhyuk.daedo.R
import kotlinx.android.synthetic.main.viewpager_image.view.*


open class ImageSliderAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    private val items: ArrayList<Fragment> = ArrayList() //tab 의 view pager 에 들어갈 프레그먼트를 저장하는 리스트
    private var images: ArrayList<String> = ArrayList() //tab 의 view pager 에 들어갈 이미지를 저장하는 리스트
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var view: View
    private lateinit var context: Context

    fun addItem(item: Fragment, image: ArrayList<String>, context: Context) {
        items.add(item) //tab 의 view pager 에 들어갈 프레그먼트를 추가
        images = image
        this.context = context
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = layoutInflater.inflate(R.layout.viewpager_image, container)
        Glide.with(context)
            .load(images[position])
            .into(view.imageSliderItem)
        container.addView(view)
        return view
    }

    override fun getItem(position: Int): Fragment {
        return items[position] //position 에 들어온 값에 따라서 맞는 프레그먼트를 반환
    }

    override fun getCount(): Int {
        return items.size //tab 의 갯수를 item 의 크기 만큼  반환
    }
}