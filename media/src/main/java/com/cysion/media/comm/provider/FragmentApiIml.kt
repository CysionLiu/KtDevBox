package com.cysion.media.comm.provider

import android.support.v4.app.Fragment
import com.cysion.media.ui.fragment.NewsFragment
import com.cysion.media.ui.fragment.VideoFragment
import com.cysion.router.media.service.FragmentApi
import io.github.prototypez.appjoint.core.ServiceProvider

@ServiceProvider
class FragmentApiIml:FragmentApi {
    override fun createNewsFragment():Fragment {
        return NewsFragment()
    }

    override fun createVideoFragment():Fragment {
        return VideoFragment()
    }

}