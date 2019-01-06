package com.cysion.media

import android.app.Application
import com.cysion.ktbox.utils.logd
import com.cysion.media.net.MediaUrls
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.github.prototypez.appjoint.core.ModuleSpec

/*
主app的application调用生命周期方法时，会对应调用该类的对应方法
 */

@ModuleSpec
class MediaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MediaUrls.debug=true
        logd(MediaUrls.SERVER_NEWS)
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}