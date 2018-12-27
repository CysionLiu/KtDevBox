package com.cysion.uibox.toast

import android.app.Activity
import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import com.cysion.uibox.R
import com.dovar.dtoast.DToast

fun Activity.toast(msg: String) {
    toToast(this, msg)
}

fun Fragment.toast(msg: String) {
    if (activity == null) {
        return
    }
    toToast(activity as Context, msg)
}

fun Context.toast(msg: String) {
    toToast(this, msg)
}

fun Activity.toast(@StringRes msgId: Int) {
    if (msgId == 0) {
        return
    }
    val msg = getString(msgId)
    toToast(this, msg)
}

fun Fragment.toast(@StringRes msgId: Int) {
    if (activity == null) {
        return
    }
    if (msgId == 0) {
        return
    }
    val msg = getString(msgId)
    toToast(activity as Context, msg)
}

fun Context.toast(@StringRes msgId: Int) {
    if (msgId == 0) {
        return
    }
    val msg = getString(msgId)
    toToast(this, msg)
}

private fun toToast(activity: Context, msg: String) {
    if (TextUtils.isEmpty(msg)) {
        return
    }
    val view = LayoutInflater.from(activity).inflate(R.layout.toast_normal, null)
    view.findViewById<TextView>(R.id.tv_text).text = msg
    DToast.make(activity)
        .setView(view)
        .setGravity(Gravity.BOTTOM, 0, (activity.resources.displayMetrics.density * 50).toInt())
        .show()
}