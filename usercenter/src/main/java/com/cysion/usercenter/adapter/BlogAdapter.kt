package com.cysion.usercenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseAdapter
import com.cysion.ktbox.base.BaseViewHolder
import com.cysion.other._setOnClickListener
import com.cysion.usercenter.R
import com.cysion.usercenter.adapter.BlogAdapter.Companion.COLLECT
import com.cysion.usercenter.adapter.BlogAdapter.Companion.DEL
import com.cysion.usercenter.adapter.BlogAdapter.Companion.EDIT
import com.cysion.usercenter.adapter.BlogAdapter.Companion.PRIDE
import com.cysion.usercenter.adapter.BlogAdapter.Companion.USER_PAGE
import com.cysion.usercenter.entity.Blog
import kotlinx.android.synthetic.main.item_blog_list.view.*


/**
 * pageType:页面类型，用于跨页面复用
 * 这里，博客列表和某个用户博客列表是不一样的展现
 */
class BlogAdapter(mEntities: MutableList<Blog>, mContext: Context, val pageType: Int = COMMON_PAGE) :
    BaseAdapter<Blog>(mEntities, mContext) {

    companion object {

        //页面类型
        const val COMMON_PAGE = 10000
        const val USER_PAGE = 10001
        const val COLLECT = 10002

        //点击事件
        const val PRIDE = 2000
        const val COMMENT = 2001
        const val EDIT = 2002
        const val DEL = 2003
    }

    //视图类型
    val SMALL = 100
    val BIG = 200

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Blog> {
        if (viewType == SMALL) {
            return InnerBlogHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_blog_list, parent, false)
                , pageType
            )
        } else {
            return InnerBlogHolderBig(
                LayoutInflater.from(mContext).inflate(R.layout.item_blog_list_big, parent, false)
                , pageType
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mEntities[position].isLargeIcon == 0) SMALL else BIG
    }
}

class InnerBlogHolder(itemView: View, val pagetype: Int) :
    BaseViewHolder<Blog>(itemView) {
    override fun fillData(obj: Blog, position: Int) {
        obj?.apply {

            if (pagetype == USER_PAGE) {
                itemView.llEdit.visibility = View.VISIBLE
                itemView.llDel.visibility = View.VISIBLE
                itemView.tvPride.isClickable = false
            } else if (pagetype == COLLECT) {
                itemView.llEdit.visibility = View.GONE
                itemView.llPride.visibility = View.GONE
                itemView.llComment.visibility = View.GONE
                itemView.llDel.visibility = View.VISIBLE
                itemView.tvContent.visibility = View.GONE
            }

            itemView.ivPride.isSelected = if (isPrided == 1) true else false
            Glide.with(mContext).load(obj.icon)
                .apply(RequestOptions.placeholderOf(R.mipmap.place_holder))
                .into(itemView.ivBlogCover)
            itemView.tvBlogTitle.text = obj.title
            itemView.tvPride.text = "${obj.prideCount}"
            itemView.tvComment.text = "0"
            itemView.tvContent.text = obj.text
            itemView.tvCreateTime.text = obj.createStamptime

            itemView.llPride._setOnClickListener {
                mOnTypeClickListener?.invoke(obj, position, PRIDE)
            }
            itemView.llEdit._setOnClickListener {
                mOnTypeClickListener?.invoke(obj, position, EDIT)
            }
            itemView.llDel._setOnClickListener {
                mOnTypeClickListener?.invoke(obj, position, DEL)
            }
        }
    }

}

class InnerBlogHolderBig(itemView: View, val pagetype: Int) :
    BaseViewHolder<Blog>(itemView) {
    override fun fillData(obj: Blog, position: Int) {
        obj?.apply {
            if (pagetype == USER_PAGE) {
                itemView.llEdit.visibility = View.VISIBLE
                itemView.llDel.visibility = View.VISIBLE
                itemView.tvPride.isClickable = false
            }else if (pagetype == COLLECT) {
                itemView.llEdit.visibility = View.GONE
                itemView.llPride.visibility = View.GONE
                itemView.llComment.visibility = View.GONE
                itemView.llDel.visibility = View.VISIBLE
                itemView.tvContent.visibility = View.GONE
            }
            itemView.ivPride.isSelected = if (isPrided == 1) true else false
            Glide.with(mContext).load(obj.icon)
                .apply(RequestOptions.placeholderOf(R.mipmap.place_holder_big))
                .into(itemView.ivBlogCover)
            itemView.tvBlogTitle.text = obj.title
            itemView.tvPride.text = "${obj.prideCount}"
            itemView.tvComment.text = "0"
            itemView.tvContent.text = obj.text
            itemView.tvCreateTime.text = obj.createStamptime

            itemView.llPride._setOnClickListener {
                mOnTypeClickListener?.invoke(obj, position, PRIDE)
            }
            itemView.llEdit._setOnClickListener {
                mOnTypeClickListener?.invoke(obj, position, EDIT)
            }
            itemView.llDel._setOnClickListener {
                mOnTypeClickListener?.invoke(obj, position, DEL)
            }

        }
    }

}