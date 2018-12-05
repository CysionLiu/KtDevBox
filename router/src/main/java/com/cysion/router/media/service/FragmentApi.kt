package com.cysion.router.media.service

import android.support.v4.app.Fragment

interface FragmentApi {

    fun createAudioFragment():Fragment
    fun createVideoFragment():Fragment

}