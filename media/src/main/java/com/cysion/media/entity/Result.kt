package com.cysion.media.entity

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers


data class Result<T>(val code: Int, val msg: String, val data: T)
class RxMap<T> : Function<Result<T>, T> {
    override fun apply(t: Result<T>): T {
        if (t.code == 200) {
            throw Exception("kjsdf")
        }
        return t.data
    }
}

object RxTransformer2 {
    fun <T> mainIo(): ObservableTransformer<Result<T>, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .map(RxMap())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
