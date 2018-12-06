package com.cysion.media.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.media.entity.RxTransformer2
import com.cysion.media.net.MediaCaller
import com.cysion.media.net.NetEventType
import com.cysion.media.ui.iview.MediaView
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe

class MediaPresenter : BasePresenter<MediaView>() {

    fun getNewsList() {
        MediaCaller.api.getNewList()
            .compose(RxTransformer2.mainIo())
            ._subscribe {
                _onNext {
                        attchedView?.setNewsList(it)
                }
                _onError {
                    error(500, it.message!!, NetEventType.GET_NEWS_LIST)
                }
            }.addTo(compositeDisposable)
    }

}