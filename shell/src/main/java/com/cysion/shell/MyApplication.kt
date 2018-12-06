package com.cysion.shell

import android.app.Application
import com.cysion.ktbox.Box
import io.github.prototypez.appjoint.core.AppSpec

@AppSpec
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Box.init(this,true)
    }
}