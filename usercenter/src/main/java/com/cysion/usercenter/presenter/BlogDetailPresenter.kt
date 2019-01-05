package com.cysion.usercenter.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.net.UserCaller
import com.cysion.usercenter.ui.iview.BlogDetailView

class BlogDetailPresenter : BasePresenter<BlogDetailView>() {


    fun pride(blog: Blog) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.prideBlog(blog.blogId)
            .compose(BaseResponseRx.validateToMain2())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        attchedView?.stopLoad()
                        blog.isPrided = 1
                        blog.prideCount++
                        prideOk(blog.blogId)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

    fun unPride(blog: Blog) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.unPrideBlog(blog.blogId)
            .compose(BaseResponseRx.validateToMain2())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        attchedView?.stopLoad()
                        blog.isPrided = 0
                        blog.prideCount--
                        unprideOk(blog.blogId)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }


    fun collect(blogid: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.collectBlog(blogid)
            .compose(BaseResponseRx.validateToMain2())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        collectOk(blogid)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)

    }

    fun unCollect(blogid: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.unCollectBlog(blogid)
            .compose(BaseResponseRx.validateToMain2())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        unCollectOk(blogid)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)

    }

    fun comment(blogId: String, content: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.commentBlog(blogId, content)
            .compose(BaseResponseRx.validateToMain2())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        commentOk(blogId)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

    //获取该博客的评论
    fun getComments(blogId: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.getComments(blogId)
            .compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        onGetComments(it)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }


    fun getBlog(blogId: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.getBlog(blogId)
            .compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        onGetBlog(it)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }


}