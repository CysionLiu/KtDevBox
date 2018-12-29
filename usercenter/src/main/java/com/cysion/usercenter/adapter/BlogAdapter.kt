package com.cysion.usercenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseAdapter
import com.cysion.ktbox.base.BaseViewHolder
import com.cysion.usercenter.R
import com.cysion.usercenter.entity.Blog
import kotlinx.android.synthetic.main.item_blog_list.view.*

class BlogAdapter(mEntities: MutableList<Blog>, mContext: Context) : BaseAdapter<Blog>(mEntities, mContext) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Blog> {
        return InnerBlogHolder(LayoutInflater.from(mContext).inflate(R.layout.item_blog_list, parent, false))
    }
}

class InnerBlogHolder(itemView: View) : BaseViewHolder<Blog>(itemView) {
    override fun fillData(obj: Blog, position: Int) {
        obj?.apply {
            Glide.with(mContext).load(obj.icon)
                .apply(RequestOptions.placeholderOf(R.mipmap.place_holder))
                .into(itemView.ivBlogCover)
            itemView.tvBlogTitle.text = obj.title
            itemView.tvPride.text = "${obj.prideCount}"
            itemView.tvComment.text = "0"
            itemView.tvContent.text = obj.text
            itemView.tvCreateTime.text = obj.createStamptime
        }
    }

}