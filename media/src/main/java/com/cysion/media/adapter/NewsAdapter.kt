package com.cysion.media.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseAdapter
import com.cysion.ktbox.base.BaseViewHolder
import com.cysion.ktbox.image.RoundTransform
import com.cysion.media.R
import com.cysion.media.entity.NewsInfo
import kotlinx.android.synthetic.main.item_news_list.view.*

class NewsAdapter(mEntities: MutableList<NewsInfo>, mContext: Context) :
    BaseAdapter<NewsInfo>(mEntities, mContext) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<NewsInfo> {
        return NewsInnerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_list, parent, false))
    }
}

class NewsInnerHolder(itemView: View) : BaseViewHolder<NewsInfo>(itemView) {

    override fun fillData(obj: NewsInfo, position: Int) {
        itemView?.apply {
            tvCate.text = obj.category
            tvTag.text = obj.source
            tvTitle.text = obj.title
            tvContent.text = obj.digest
            tvTime.text = obj.ptime
            if (obj.picInfo != null && obj.picInfo.size > 0) {
                Glide.with(context)
                    .load(obj.picInfo?.get(0)?.url)
                    .apply(RequestOptions.placeholderOf(R.mipmap.place_holder_big))
                    .apply(RequestOptions.bitmapTransform(RoundTransform(6)))
                    .into(ivCoverView)
                ivCoverView.visibility = View.VISIBLE
            } else {
                ivCoverView.visibility = View.GONE
            }
        }
    }
}

