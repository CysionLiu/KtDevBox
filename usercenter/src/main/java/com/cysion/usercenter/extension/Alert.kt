package com.cysion.usercenter.extension

import android.content.Context
import android.widget.Toast

fun Context.tos(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}