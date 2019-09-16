package com.cysion.usercenter.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.ktbox.net.ApiException
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.entity.Carousel

interface SquareView : IView {
    fun onGetCarousels(carousels: MutableList<Carousel>)

    fun onGetBlogs(blogs: MutableList<Blog>)

    fun onGetBlogError(e:ApiException)

    fun prideOk(index: Int)

    fun unprideOk(index: Int)
}
