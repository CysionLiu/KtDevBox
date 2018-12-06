package com.cysion.media.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.ktbox.utils.RxTransformer
import com.cysion.media.net.MediaCaller
import com.cysion.media.net.NetEventType
import com.cysion.media.ui.iview.MediaView
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe

class MediaPresenter : BasePresenter<MediaView>() {

    fun getNewsList() {
        MediaCaller.api.getNewList()
            .compose(RxTransformer.mainIo())
            ._subscribe {
                _onNext {
                    if (it.code != 200) {
                        error(it.code, it.msg, NetEventType.GET_NEWS_LIST)
                    } else {
                        attchedView?.setNewsList(it.data)
                    }
                }
                _onError {
                    error(500, it.message!!, NetEventType.GET_NEWS_LIST)
                }
            }.addTo(compositeDisposable)
    }

}