package com.cysion.media.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.media.entity.Data

interface MediaView:IView {
    fun setNewsList(data: Data)
}