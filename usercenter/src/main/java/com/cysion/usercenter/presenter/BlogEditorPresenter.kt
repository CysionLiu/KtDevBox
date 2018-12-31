package com.cysion.usercenter.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe
import com.cysion.usercenter.net.UserCaller
import com.cysion.usercenter.ui.iview.BlogEditorView

class BlogEditorPresenter : BasePresenter<BlogEditorView>() {

    fun createBlog(title: String, text: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.createBlog(title, text)
            .compose(BaseResponseRx.threadline())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        createDone()
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

    fun updateBlog(title: String, text: String, blogId: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.updateBlog(title, text, blogId)
            .compose(BaseResponseRx.threadline())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        updateDone()
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

}