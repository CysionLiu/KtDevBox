package com.cysion.temp

import android.content.Context
import android.widget.Toast
import com.cysion.ktbox.Box
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.base.ITEM_CLICK
import com.cysion.ktbox.logd
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        Box.init(self.applicationContext, false)
        tvShow.setOnClickListener {
            shortTip("hello KtDevBox")
            logd("懒加载-->" + javaClass.simpleName)
        }
    }
}

fun Context.shortTip(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
