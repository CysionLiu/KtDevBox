package com.cysion.usercenter.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.net.UserCaller
import com.cysion.usercenter.ui.iview.SquareView

class SquarePresenter : BasePresenter<SquareView>() {

    fun getCarousel() {
        checkViewAttached()
        UserCaller.api.getgetCarousel()
            .compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.setCarousels(it)
                }
                _onError {
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

    fun getBlogs(pageNum: Int) {
        checkViewAttached()
        UserCaller.api.getBlogList(page = pageNum)
            .compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.stopLoad()
                    attchedView?.setBlogList(it)
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
                        attchedView?.stopLoad()
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
                        attchedView?.stopLoad()
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