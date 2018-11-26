package com.cysion.ktbox.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : AppCompatActivity() {

    protected val self: Activity by lazy {
        this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId());
        EventBus.getDefault().register(this)
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun fromEventbus(app: Application) {
        //仅占位，防止子类继承出问题
        //子类可以仿照这种写法，但是事件类型必须是自定义的
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open protected fun initData() {
    }
}