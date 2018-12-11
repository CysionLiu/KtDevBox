package com.cysion.ktbox.net

import okhttp3.Interceptor
import retrofit2.Retrofit


/**
 * 网络请求基础类
 * 在需要使用时，建议是（每个域名或者组件内分别）使用其子类，以便根据情况更改baseurl
 * 或者添加一些公共参数，headers等.
 * initInstantly,默认true,立即初始化调用init; false,需要使用者初始化，用于添加拦截器
 * 例如
 * 声明：object Caller : BaseCaller<Api>("you baseurl", Api::class.java)
 * interface Api {
      @GET()
      fun test()
   }
 *  使用: Caller.api.test()或者Caller.loadApi<Api>().test()
 *  若是要添加一些公共参数或者header
 *  可以这样初始化，instantlyInit设为false
 *  object Caller : BaseCaller<Api>("you baseurl", Api::class.java，false)
 *  Caller.addInterceptor(your interceptor).init()
 */

abstract class BaseCaller<T>(val baseUrl: String, val apiClass: Class<T>, val initInstantly: Boolean=true) {
    var hasInited = false
        private set
    private val interceptors: MutableList<Interceptor> = mutableListOf()
    lateinit var mRetrofit: Retrofit
        private set
    val api: T by lazy { mRetrofit.create(apiClass) }

    fun addInterceptor(i: Interceptor): BaseCaller<T> {
        interceptors.add(i)
        return this
    }

    init {
        if (initInstantly) {
            init()
        }
    }

    fun init() {
        if (hasInited) {
            return
        }
        hasInited = true

        val okHttpClientBuilder = BaseClient.mOkHttpClient.newBuilder()
        interceptors.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }
        val okHttpClient = okHttpClientBuilder.build()

        mRetrofit = BaseClient.mRetrofit.newBuilder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    }

    inline fun <reified T> loadApi(): T {
        if (!hasInited) {
            throw Exception("One caller should invoke init() before loadApi")
        }
        return mRetrofit.create(T::class.java)
    }
}