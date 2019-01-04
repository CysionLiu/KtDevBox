package com.cysion.usercenter.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.entity.CollectEntity

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

    fun prideOk(blogId:String)

    fun unprideOk(blogId:String)

    fun collectOk(blogId:String)

    fun unCollectOk(blogId:String)

    fun commentOk(blogId:String)

    fun getComments()

}

interface CollectView : IView {

    fun setList(dataList: MutableList<CollectEntity>)

    fun onCanceled()

}
