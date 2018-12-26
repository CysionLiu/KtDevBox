package com.cysion.usercenter.net

object UserUrls {
    private var hasInited = false
    var debug: Boolean = true
        set(value) {
            if (!hasInited) {
                field = value
                hasInited = true
            } else {
                throw Exception("不能两次初始化")
            }
        }

    val HOST by lazy {
        if (debug) "http://148.70.2.189/app/" else "http://148.70.2.189/app/"
    }
}