package com.cysion.usercenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cysion.ktbox.base.BaseFragment

class SquareFragment: BaseFragment() {


    override fun getLayoutId(): Int =0

    override fun initView() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TextView(context).apply { text="广场" }
    }

    override fun closeMvp() {
    }
}