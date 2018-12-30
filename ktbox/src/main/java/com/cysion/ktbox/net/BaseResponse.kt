package com.cysion.ktbox.net

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * 数据基类；若字段不一致，可仿照再写一个
 */
data class BaseResponse<T>(val code: Int, val msg: String, val data: T) {
    fun isSuccessful(): Boolean {
        return code == 200
    }
}

object BaseResponseRx {
    //    当T为null时，不能调用map方法，应使用threadline()；此时，code完全能表明结果状态
    fun <T> validateToMain(): ObservableTransformer<BaseResponse<T>, T> {
        return ObservableTransformer { observable ->
            observable.map(validate()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> validate(): Function<BaseResponse<T>, T> {
        return Function {
            if (!it.isSuccessful()) {
                throw ApiException(it.code, it.msg)
            }
            it.data
        }
    }

    fun <T> threadline(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}
