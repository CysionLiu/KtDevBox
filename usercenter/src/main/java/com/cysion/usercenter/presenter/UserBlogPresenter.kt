package com.cysion.usercenter.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.net.UserCaller
import com.cysion.usercenter.ui.iview.UserBlogListView

class UserBlogPresenter : BasePresenter<UserBlogListView>() {

    fun getBlogs(page: Int = 1) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.getUserBlogList(page)
            .compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        setList(it)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)

    }

    fun deleteBlog(blogId: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.delBlog(blogId)
            .compose(BaseResponseRx.threadline())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        delSuccessful()
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

    fun pride(blog: Blog, pos: Int) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.prideBlog(blog.blogId)
            .compose(BaseResponseRx.threadline())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        blog.isPrided = 1
                        blog.prideCount++
                        prideOk(pos)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

    fun unPride(blog: Blog, pos: Int) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.unPrideBlog(blog.blogId)
            .compose(BaseResponseRx.threadline())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        blog.isPrided = 0
                        blog.prideCount--
                        unprideOk(pos)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

}