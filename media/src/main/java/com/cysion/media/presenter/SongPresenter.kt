package com.cysion.media.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.media.net.BaseRespRx
import com.cysion.media.net.ChannelCaller
import com.cysion.media.ui.iview.SongView
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe

class SongPresenter:BasePresenter<SongView>() {

    fun getSongs(channelName:String){
        checkViewAttached()
        attchedView?.loading()
        ChannelCaller.api.getChannelDetail(channelName)
            .compose(BaseRespRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.stopLoad()
                    attchedView?.setSongList(it.songlist)
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)

    }
}