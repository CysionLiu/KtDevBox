package com.cysion.media.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.media.entity.ChannelInfo

interface ChnView : IView {
    fun setChannels(datalist: MutableList<ChannelInfo>)
}