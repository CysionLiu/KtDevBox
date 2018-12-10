package com.cysion.usercenter.operator

import android.support.v4.app.Fragment
import com.cysion.usercenter.R
import com.cysion.usercenter.comm.Resolver
import com.cysion.usercenter.ui.fragment.SquareFragment
import com.cysion.usercenter.ui.fragment.UserFragment
import java.util.*

object ListVals {
    fun getFragments(): MutableList<Fragment> {
        val fragmentArrayList = ArrayList<Fragment>()
        fragmentArrayList.add(SquareFragment())
        fragmentArrayList.add(Resolver.mediaFragmentApi.createNewsFragment())
        fragmentArrayList.add(Resolver.mediaFragmentApi.createVideoFragment())
        fragmentArrayList.add(UserFragment())
        return fragmentArrayList
    }

    fun getIcons(): IntArray {
        val ints = IntArray(4)
        ints[0] = R.drawable.home_study_selector
        ints[1] = R.drawable.home_hot_selector
        ints[2] = R.drawable.home_dingzhi_selector
        ints[3] = R.drawable.home_user_selector
        return ints
    }

    fun getTitles(): MutableList<String> {
        val strings = ArrayList<String>()
        strings.add("广场")
        strings.add("新闻")
        strings.add("电台")
        strings.add("我的")
        return strings
    }
}