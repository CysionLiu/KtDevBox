package com.cysion.usercenter.helper

import android.app.Activity
import android.widget.TextView
import com.cysion.uibox.dialog.Alert
import com.cysion.uibox.dialog.CANCEL
import com.cysion.uibox.dialog.CONFIRM
import com.cysion.uibox.dialog.OnTypeClickListener
import com.cysion.usercenter.R
import com.cysion.usercenter.entity.Blog

object BlogHelper {

    //根据id得到列表中的博客
    fun getBlog(blogid: String, datalist: MutableList<Blog>): Blog? {
        var blog = null
        datalist.forEach {
            if (it.blogId.equals(blogid)) {
                return it
            }
        }
        return null
    }

    fun comment(
        src: Activity,
        clickListener: OnTypeClickListener?
    ) {
        val view = Alert.setup(src, R.layout.dialog_comment, 0.8f, false, clickListener)
        val editor = view.findViewById<TextView>(R.id.etComment)
        view.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
            Alert.close()
            clickListener?.invoke(CANCEL, "")
        }
        view.findViewById<TextView>(R.id.tv_confirm).setOnClickListener {
            Alert.close()
            clickListener?.invoke(CONFIRM, editor.text.toString().trim())
        }
    }

}