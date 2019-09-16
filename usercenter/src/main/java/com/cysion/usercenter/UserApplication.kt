package com.cysion.usercenter

import android.app.Application
import com.cysion.usercenter.net.UserUrls
import io.github.prototypez.appjoint.core.ModuleSpec

/*
主app的application调用生命周期方法时，会对应调用该类的对应方法
 */
@ModuleSpec
class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        UserUrls.debug = false
    }

}