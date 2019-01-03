package com.cysion.ktbox.net

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
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
    //当T为null时，不能调用map方法，应该用validateToMain2()；此时，code完全能表明结果状态
    fun <T> validateToMain(): ObservableTransformer<BaseResponse<T>, T> {
        return ObservableTransformer { observable ->
            observable.map({
                if (!it.isSuccessful()) {
                    throw ApiException(it.code, it.msg)
                }
                it.data
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    //T字段不存在或是为null时，用这个
    fun <T> validateToMain2(): ObservableTransformer<BaseResponse<T>, BaseResponse<T>> {
        return ObservableTransformer { observable ->
            observable.map({
                if (!it.isSuccessful()) {
                    throw ApiException(it.code, it.msg)
                }
                it
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}
