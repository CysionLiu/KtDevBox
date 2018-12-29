package com.cysion.uibox.dialog

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.cysion.uibox.R

/**
 * Created by cysion on 2018\2\11 0011.
 */
typealias OnTypeClickListener = (type: Int, msg: String) -> Unit

const val CONFIRM = 1
const val CANCEL = 0


object Alert {
    private var mDialog: AlertDialog? = null


    //初始化对话框，并弹出；上层弹窗时不调用；上层 定制弹窗 时可使用
    fun setup(
        src: Activity,
        layoutId: Int,
        widthMulti: Float,
        cancelable: Boolean,
        clickListener: OnTypeClickListener?,
        dim: Float = 0f
    ): View {
        mDialog?.dismiss()
        val builder = AlertDialog.Builder(src)
        val inflater = LayoutInflater.from(src)
        val view = inflater.inflate(layoutId, null)
        val dialog = builder.create()
        val window = dialog.window
        dialog.show()
        dialog.setCanceledOnTouchOutside(cancelable)
        dialog.setCancelable(cancelable)
        //摆脱token的限制，注意清单文件alert权限
        val p = window!!.attributes // 获取对话框当前的参数值
        window.decorView.setBackgroundColor(0X00000000)
        p.width = (src.resources.displayMetrics.widthPixels * widthMulti).toInt()
        window.attributes = p
        window.setDimAmount(dim)
        window.setBackgroundDrawable(null)
        dialog.window!!.setContentView(view)//自定义布局应该在这里添加，要在dialog.show()的后面
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        mDialog = dialog
        return view
    }


    //关闭对话框
    fun close() {
        mDialog?.dismiss()
        mDialog = null
    }


    //加载框
    fun loading(src: Activity) {
        src.runOnUiThread {
            setup(src, R.layout.dialog_loading, 1f, true, null)
        }
    }

    //正常弹出，带有确定和取消，例子：
    /**
     * Alert.normal(self,"title","text",ok = "confirm"){
    type, msg ->

    }
     */
    fun normal(
        src: Activity,
        title: String,
        content: String,
        cancel: String = "取消",
        ok: String = "确定",
        clickListener: OnTypeClickListener?
    ) {
        val view = setup(src, R.layout.dialog_normal, 0.8f, true, clickListener, dim = 0.3f)
        view.findViewById<TextView>(R.id.tv_title).text = title
        view.findViewById<TextView>(R.id.tv_content).text = content
        view.findViewById<TextView>(R.id.tv_cancel).apply {
            text = cancel
        }.setOnClickListener {
            mDialog?.dismiss()
            clickListener?.invoke(CANCEL, "")
        }
        view.findViewById<TextView>(R.id.tv_confirm).apply {
            text = ok
        }.setOnClickListener {
            mDialog?.dismiss()
            clickListener?.invoke(CONFIRM, "")
        }
    }
}
