package com.cysion.usercenter.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.usercenter.R
import com.cysion.usercenter.entity.Carousel
import kotlinx.android.synthetic.main.item_carousel_square.view.*

typealias ItemClickListener = (obj: Carousel) -> Unit

class HomeTopPageAdapter(private val mContext: Context, private val datalist: MutableList<Carousel>) :
    PagerAdapter() {

    private var mListener: ItemClickListener? = null

    override fun getCount(): Int {
        return datalist.size
    }

    fun setItemClickListener(listener: ItemClickListener) {
        mListener = listener
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    /**
     * 返回要显示的view,即要显示的视图
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_carousel_square, null)
        Glide.with(mContext).load(datalist[position].picUrl).apply(
            RequestOptions().placeholder(R.mipmap.place_holder_big)
        ).into(view.ivCover)
        view.setOnClickListener {
            mListener?.invoke(datalist[position])
        }
        container.addView(view)    //这一步很重要
        return view
    }

    /**
     * 销毁条目
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
