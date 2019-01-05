package com.cysion.usercenter.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.entity.CollectEntity
import com.cysion.usercenter.entity.CommentEntity

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

    fun onGetBlog(blog:Blog)

    fun prideOk(blogId:String)

    fun unprideOk(blogId:String)

    fun collectOk(blogId:String)

    fun unCollectOk(blogId:String)

    fun commentOk(blogId:String)

    fun onGetComments(datalist:MutableList<CommentEntity>)

}

interface CollectView : IView {

    fun setList(dataList: MutableList<CollectEntity>)

    fun onCanceled()

}
