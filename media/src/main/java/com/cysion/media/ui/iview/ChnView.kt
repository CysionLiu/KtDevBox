package com.cysion.media.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.media.entity.ChannelInfo
import com.cysion.media.entity.Song

interface ChnView : IView {
    fun setChannels(datalist: MutableList<ChannelInfo>)
}

interface SongView :IView{
    fun setSongList(songs:MutableList<Song>)
}