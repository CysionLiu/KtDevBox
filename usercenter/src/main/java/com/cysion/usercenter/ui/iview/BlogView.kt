package com.cysion.usercenter.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.usercenter.entity.Blog

interface UserBlogListView : IView {

    fun setList(blogList: MutableList<Blog>)

    fun delSuccessful()

    fun prideOk(index:Int)

    fun unprideOk(index:Int)

}

interface BlogEditorView : IView {

    fun createDone()

    fun updateDone()
}

interface BlogDetailView:IView{


    fun prideOk(index:Int)

    fun unprideOk(index:Int)

    fun collect(index:Int)

    fun unCollect(index:Int)

    fun comment()

    fun getComments()

}