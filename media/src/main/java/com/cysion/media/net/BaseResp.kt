package com.cysion.media.net

import com.cysion.ktbox.net.ApiException
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
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
    //    当T为null时，不能调用map方法，应使用BaseResponseRx.threadline()；此时，code完全能表明结果状态
    fun <T> validateToMain(): ObservableTransformer<BaseResp<T>, T> {
        return ObservableTransformer { observable ->
            observable.map(validate()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> validate(): Function<BaseResp<T>, T> {
        return Function {
            if (!it.isSuccessful()) {
                throw ApiException(it.code, it.msg)
            }
            it.result
        }
    }
}
