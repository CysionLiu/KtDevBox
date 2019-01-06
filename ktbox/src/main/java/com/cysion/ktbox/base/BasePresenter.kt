package com.cysion.ktbox.base

import com.cysion.ktbox.listener.IView
import com.cysion.ktbox.net.ErrorHandler
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : IView> {

    //只读
    var attchedView: V? = null
        private set

    //rx事件流管理
    protected val compositeDisposable = CompositeDisposable()

    //关联view与presenter
    fun attach(v: V) {
        attchedView = v
    }

    fun detach() {
        attchedView = null
        //清除事件流
        compositeDisposable.clear()
    }

    //统一错误处理
    fun error(e:Throwable) {
        val error = ErrorHandler.handle(e)
        attchedView?.error(error.errorCode, error.errorMsg)
    }

    fun checkViewAttached() {
        if (attchedView == null) {
            throw ViewNotAttchedException()
        }
    }
}

private class ViewNotAttchedException() : Exception("Please call attach method firstly")