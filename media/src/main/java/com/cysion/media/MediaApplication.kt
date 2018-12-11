package com.cysion.media

import android.app.Application
import com.cysion.ktbox.utils.logd
import com.cysion.media.constant.Urls
import com.cysion.media.net.ChannelCaller
import io.github.prototypez.appjoint.core.ModuleSpec
import okhttp3.Interceptor
import okhttp3.Response

@ModuleSpec
class MediaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Urls.debug=true
        ChannelCaller.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newReq = chain.request().newBuilder().addHeader("token", "USEFSDIF4143123_TTTLSJDFS").build()
                return chain.proceed(newReq)
            }
        }).init()
        logd(Urls.SERVER_NEWS)
    }
}