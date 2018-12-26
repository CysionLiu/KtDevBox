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
import com.cysion.media.entity.Song
import kotlinx.android.synthetic.main.item_songs_list.view.*

class SongAdapter(mEntities: MutableList<Song>, mContext: Context) : BaseAdapter<Song>(mEntities, mContext) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Song> {
        return SongInnerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_songs_list, parent, false))
    }
}

class SongInnerHolder(itemView: View) : BaseViewHolder<Song>(itemView) {
    override fun fillData(obj: Song, position: Int) {
        obj.apply {
            Glide.with(mContext).load(obj.thumb)
                .apply(RequestOptions.placeholderOf(R.mipmap.place_holder))
                .into(itemView.ivThumb)
            itemView.tvSongName.text = obj.title
            if (obj.artist != null) {
                itemView.tvAuthor.text = "作家: ${obj.artist}"
            }
        }
    }
}