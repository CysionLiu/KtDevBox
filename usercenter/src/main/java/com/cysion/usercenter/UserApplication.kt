package com.cysion.usercenter

import android.app.Application
import com.cysion.usercenter.helper.UserCache
import com.cysion.usercenter.net.UserCaller
import com.cysion.usercenter.net.UserUrls
import io.github.prototypez.appjoint.core.ModuleSpec
import okhttp3.Interceptor
import okhttp3.Response

@ModuleSpec
class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        UserUrls.debug = true
        UserCaller.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val builder = chain.request().newBuilder()
                builder.addHeader("userid", UserCache.userId)
                builder.addHeader("token", UserCache.token)
                return chain.proceed(builder.build())
            }
        }).init()
    }
}