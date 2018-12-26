package com.cysion.media.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseAdapter
import com.cysion.ktbox.base.BaseViewHolder
import com.cysion.media.R
import com.cysion.media.entity.ChannelInfo
import kotlinx.android.synthetic.main.item_chn_list.view.*

class MusicChnAdapter(mEntities: MutableList<ChannelInfo>, mContext: Context) :
    BaseAdapter<ChannelInfo>(mEntities, mContext) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChannelInfo> {
        return ChnInnerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_chn_list, parent, false))
    }
}

class ChnInnerHolder(itemView: View) : BaseViewHolder<ChannelInfo>(itemView) {
    override fun fillData(obj: ChannelInfo, position: Int) {
        itemView.apply {
            tvMainChannel.text = obj.cate_sname
            tvSpec.text = obj.name
            Glide.with(context).load(obj.thumb)
                .apply(RequestOptions.placeholderOf(R.mipmap.place_holder))
                .into(ivChannel)
        }
    }
}