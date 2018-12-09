package com.cysion.media.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.ktbox.net.BaseRespRx.validateToMain
import com.cysion.ktbox.net.ErrorHandler
import com.cysion.media.net.MediaCaller
import com.cysion.media.ui.iview.MediaView
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe

class NewsPresenter : BasePresenter<MediaView>() {

    fun getNewsList() {
        MediaCaller.api.getNewList()
            .compose(validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.setNewsList(it)
                }
                _onError {
                    error(ErrorHandler.handle(it))
                }
            }.addTo(compositeDisposable)
    }
}