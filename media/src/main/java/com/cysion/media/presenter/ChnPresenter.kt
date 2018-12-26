package com.cysion.media.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.media.net.BaseRespRx
import com.cysion.media.net.ChannelCaller
import com.cysion.media.ui.iview.ChnView
import com.cysion.other.addTo

class ChnPresenter : BasePresenter<ChnView>() {

    fun getChnList() {
        checkViewAttached()
        attchedView?.loading()
        ChannelCaller.api.getChannelList()
            .compose(BaseRespRx.validateToMain())
            .subscribe(
                {
                    attchedView?.apply {
                        setChannels(it[0].channellist)
                        stopLoad()
                    }
                },
                {
                    attchedView?.stopLoad()
                    error(it)
                }
            ).addTo(compositeDisposable)
    }

}