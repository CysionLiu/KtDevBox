package com.cysion.ktbox

import android.annotation.SuppressLint
import android.content.Context

/**
 * 全局变量
 * 在application里就要init
 */
@SuppressLint("StaticFieldLeak")
object Box {
    private var hasInited = false
    lateinit var context: Context
        private set
    var debug = false
        private set

    fun init(ctx: Context, debugmode: Boolean) {
        if (!hasInited) {
            context = ctx.applicationContext
            debug = debugmode
            hasInited = true
        } else {
            throw IllegalArgumentException()
        }
    }
}