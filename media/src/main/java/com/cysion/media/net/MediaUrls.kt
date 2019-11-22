package com.cysion.media.net

object MediaUrls {
    private var hasInited = false
    var debug: Boolean = true
        set(value) {
            if (!hasInited) {
                field = value
                hasInited = true
            }else{
                throw Exception("不能两次初始化")
            }
        }

    val SERVER_NEWS by lazy {
       if(debug)"https://api.apiopen.top/" else "https://api.apiopen.top/"
    }
    val SERVER_CHNS by lazy {
       if(debug)"https://api.apiopen.top/" else "https://api.apiopen.top/"
    }
}