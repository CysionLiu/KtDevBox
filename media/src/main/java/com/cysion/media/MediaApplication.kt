package com.cysion.media

import android.app.Application
import com.cysion.media.net.MediaCaller
import io.github.prototypez.appjoint.core.ModuleSpec

@ModuleSpec
class MediaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MediaCaller.init()
    }
}