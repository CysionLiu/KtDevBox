package com.cysion.usercenter.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.usercenter.entity.Blog

interface UserBlogListView : IView {

    fun setList(blogList: MutableList<Blog>)

    fun delSuccessful()

}

interface BlogEditorView : IView {

    fun createDone()

    fun updateDone()
}