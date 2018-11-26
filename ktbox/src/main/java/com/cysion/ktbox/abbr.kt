package com.cysion.ktbox

import android.util.Log

val TAG = "ktDevBox"

fun logd(msg: String) {
    if (Box.debug) {
        Log.d(TAG, msg)
    }
}

fun logi(msg: String) {
    if (Box.debug) {
        Log.i(TAG, msg)
    }
}