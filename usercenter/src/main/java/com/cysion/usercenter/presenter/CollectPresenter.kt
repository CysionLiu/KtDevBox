package com.cysion.usercenter.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe
import com.cysion.usercenter.net.UserCaller
import com.cysion.usercenter.ui.iview.CollectView

class CollectPresenter : BasePresenter<CollectView>() {

    //获得收藏列表
    fun getBlogs(page: Int = 1) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.getCollectList()
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

    //取消收藏
    fun delCol(itemId: String) {
        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.unCollectBlog(itemId)
            .compose(BaseResponseRx.validateToMain2())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        stopLoad()
                        onCanceled()
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

}