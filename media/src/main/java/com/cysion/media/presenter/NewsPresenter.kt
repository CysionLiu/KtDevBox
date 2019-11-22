package com.cysion.media.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.media.net.BaseRespRx
import com.cysion.media.net.MediaCaller
import com.cysion.media.ui.iview.NewsView
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe

class NewsPresenter : BasePresenter<NewsView>() {

    fun getNewsList() {
        checkViewAttached()
        attchedView?.loading()
        MediaCaller.api.getNewList()
            .compose(BaseRespRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        setNewsList(it)
                        stopLoad()
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }
}