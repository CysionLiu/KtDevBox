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
        if (debug) "http://172.16.168.125:8000/app/" else "http://47.94.100.160/app/"
    }
}