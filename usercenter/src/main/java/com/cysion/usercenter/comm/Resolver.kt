package com.cysion.usercenter.comm

import com.cysion.router.media.service.ActivityApi
import com.cysion.router.media.service.FragmentApi
import io.github.prototypez.appjoint.AppJoint

object Resolver {
    val mediaFragmentApi: FragmentApi = AppJoint.service(FragmentApi::class.java)
    val mediaActivityApi: ActivityApi = AppJoint.service(ActivityApi::class.java)
}