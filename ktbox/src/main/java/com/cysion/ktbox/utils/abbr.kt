package com.cysion.ktbox.utils

import android.app.Activity
import android.os.Build
import android.support.annotation.ColorInt
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.AdapterView
import com.cysion.ktbox.Box
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jaeger.library.StatusBarUtil
import com.orhanobut.logger.Logger

/**
 * 简略打印
 */
val TAG = "ktDevBox"

fun logd(msg: String) {
    if (Box.debug) {
        Logger.d(msg)
    }
}

fun logi(msg: String) {
    if (Box.debug) {
        Logger.i(msg)
    }
}

fun logw(msg: String) {
    if (Box.debug) {
        Logger.w(msg)
    }
}

//color-状态栏背景颜色
fun Activity.whiteTextTheme(@ColorInt color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        StatusBarUtil.setColor(this, color, 0)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE//恢复状态栏白色字体
    }
}

//color-状态栏背景颜色
fun Activity.darkTextTheme(@ColorInt color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        StatusBarUtil.setColor(this, color, 0)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//恢复状态栏黑色字体
    }
}

//深度回收view
fun View.gc() {
    val view = this
    if (view.background != null) {
        view.background.callback = null
    }
    if (view is ViewGroup && view !is AdapterView<*>) {
        if (view is WebView) {
            view.removeAllViews()
            view.destroy()
            return
        }
        for (i in 0 until view.childCount) {
            view.getChildAt(i).gc()
        }
        view.removeAllViews()
    }
}
//gson解析简化类
inline fun <reified T> Gson.fromjson(json: String?): T? {
    if (TextUtils.isEmpty(json)) {
        return null
    }
    return GsonBuilder().create().fromJson(json, object : TypeToken<T>() {}.type)
}