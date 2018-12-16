package com.cysion.router.media.service

import android.app.Activity

interface ActivityApi {

    fun startNewsActivity(src:Activity,title:String,link:String)
    fun startSongsActivity(src:Activity,title:String,channelName:String)


}