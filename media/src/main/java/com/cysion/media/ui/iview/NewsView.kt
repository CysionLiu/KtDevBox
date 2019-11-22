package com.cysion.media.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.media.entity.NewsInfoEntity

interface NewsView:IView {
    fun setNewsList(data: MutableList<NewsInfoEntity>)
}