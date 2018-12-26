package com.cysion.usercenter.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.targetfun._subscribe
import com.cysion.usercenter.helper.UserCache
import com.cysion.usercenter.net.UserCaller
import com.cysion.usercenter.ui.iview.LoginView

class LoginPresenter : BasePresenter<LoginView>() {

    fun login(username: String, pwd: String) {

        checkViewAttached()
        attchedView?.loading()
        UserCaller.api.login(username, pwd)
            .compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.stopLoad()
                    UserCache.save(it)
                    attchedView?.setUserInfo(it)
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }
    }
}