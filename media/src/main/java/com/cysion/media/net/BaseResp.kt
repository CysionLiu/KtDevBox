package com.cysion.media.net

import com.cysion.ktbox.net.ApiException
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 数据基类；若字段不一致，可仿照再写一个
 */
data class BaseResp<T>(val code: Int, val msg: String, val result: T) {
    fun isSuccessful(): Boolean {
        return code == 200
    }
}

object BaseRespRx {
    fun <T> validateToMain(): ObservableTransformer<BaseResp<T>, T> {
        return ObservableTransformer { observable ->
            observable.map {
                if (!it.isSuccessful()) {
                    throw ApiException(it.code, it.msg)
                }
                if (it.result == null) {
                    Any() as T
                } else {
                    it.result
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
