package com.cysion.ktbox.net

import com.cysion.ktbox.Box
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


/**
 * 网络请求基类
 * 需要使用其子类，建议是（每个域名或者组件内分别）分别维护一个
 * 1-以便根据情况更改baseurl
 * 2-或者添加一些公共参数，headers等.
 * 3-更改设置，比如builder.factory..
 * 例如，1-不需要其它操作，则直接声明
 * object Caller : BaseCaller<Api>("you baseurl", Api::class.java)
 * interface Api {
        @GET()
        fun test()
    }
 *  使用方式: Caller.api.test()或者Caller.loadApi<Api>().test()
 *
 * 2-需要其它操作，则重写子类方法
 *  object Caller2 : BaseCaller<Api>("you baseurl", Api::class.java){
 *      override fun beforeInit() {
             addInterceptor(interceptor)
        }
 *  }
 *
 *  如需builder改动一些参数，子类重写getOkHttpClientBuilder等方法
 *
 *  可在合适的位置打断点调试查看参数，具体见代码注释处
 */

abstract class BaseCaller<T>(val baseUrl: String, val apiClass: Class<T>) {

    //是否初始化了
    var hasInited = false
        private set
    //拦截器集合
    private val interceptors: MutableList<Interceptor> = mutableListOf()

    lateinit var mRetrofit: Retrofit
        private set

    //默认service api
    val api: T by lazy { mRetrofit.create(apiClass) }


    //构造函数调用
    init {
        beforeInit()
        init()
    }

    private fun init() {
        if (hasInited) {
            return
        }
        hasInited = true

        val okHttpClientBuilder = getOkHttpClientBuilder()
        interceptors.forEach {
            okHttpClientBuilder.addInterceptor(it)

        }
        okHttpClientBuilder.addInterceptor {
            //断点调试
            val request = it.request();
            it.proceed(request)
        }
        okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(getLevel()))
        val okHttpClient = okHttpClientBuilder.build()

        mRetrofit = getRetrofitBuilder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    }

    fun addInterceptor(i: Interceptor): BaseCaller<T> {
        interceptors.add(i)
        return this
    }

    private fun getLevel(): HttpLoggingInterceptor.Level {
        return if (Box.debug) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
    }

    //子类初始化之前的操作，没有操作，则不重写
    open protected fun beforeInit() {
    }


    open protected fun getOkHttpClientBuilder(): OkHttpClient.Builder {
        return BaseClient.mOkHttpClient.newBuilder()
    }

    open protected fun getRetrofitBuilder(): Retrofit.Builder {
        return BaseClient.mRetrofit.newBuilder()
    }

    //使用不同的service api
    inline fun <reified T> loadApi(): T {
        if (!hasInited) {
            throw Exception("One caller should invoke init() before loadApi")
        }
        return mRetrofit.create(T::class.java)
    }
}