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
import com.cysion.media.entity.NewsInfoEntity
import kotlinx.android.synthetic.main.item_news_list.view.*

class NewsAdapter(mEntities: MutableList<NewsInfoEntity>, mContext: Context) :
    BaseAdapter<NewsInfoEntity>(mEntities, mContext) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<NewsInfoEntity> {
        return NewsInnerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_list, parent, false))
    }
}

class NewsInnerHolder(itemView: View) : BaseViewHolder<NewsInfoEntity>(itemView) {

    override fun fillData(obj: NewsInfoEntity, position: Int) {
        itemView?.apply {
            tvTitle.text = obj.title
            tvTime.text = obj.passtime
            if (obj.image != null) {
                Glide.with(context)
                    .load(obj.image)
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

