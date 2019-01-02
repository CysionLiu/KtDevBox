package com.cysion.uibox.bar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.cysion.uibox.R
import kotlinx.android.synthetic.main.base_ui_top_bar.view.*

/**
 * Created by xianshang.liu on 2017/5/17.
 */

class TopBar : RelativeLayout {

    private var mRootView: View? = null

    private lateinit var mOnTopBarClickListener:
            ((obj: Any, pos: Pos) -> Unit)

    enum class Pos {
        LEFT, RIGHT, CENTER
    }

    enum class ELEMENT {
        TEXT, IMG, GONE
    }

    interface OnTopBarClickListener {
        fun onIconClicked(aView: View, aPosition: Pos)
    }

    constructor(context: Context) : super(context) {}


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    private fun init(aContext: Context) {
        mRootView = LayoutInflater.from(aContext).inflate(R.layout.base_ui_top_bar, this)
        flayoutLeft.setOnClickListener {
            mOnTopBarClickListener.invoke(it, Pos.LEFT)
        }
        flayoutCenter.setOnClickListener {
            mOnTopBarClickListener.invoke(it, Pos.CENTER)
        }
        flayoutRight.setOnClickListener {
            mOnTopBarClickListener.invoke(it, Pos.RIGHT)
        }
    }

    fun setOnTopBarClickListener(aOnTopBarClickListener: ((obj: Any, pos: Pos) -> Unit)) {
        mOnTopBarClickListener = aOnTopBarClickListener
    }

    fun initElements(left: ELEMENT=ELEMENT.IMG, center: ELEMENT=ELEMENT.TEXT, right: ELEMENT=ELEMENT.GONE) {
        if (left == ELEMENT.TEXT) {
            ivLeft.visibility = View.GONE
            tvLeft.visibility = View.VISIBLE
        } else if (left == ELEMENT.IMG) {
            ivLeft.visibility = View.VISIBLE
            tvLeft.visibility = View.GONE
        } else {
            flayoutLeft.visibility = View.GONE
        }

        if (center == ELEMENT.TEXT) {
            ivCenter.visibility = View.GONE
            tvTopbarTitle.visibility = View.VISIBLE
        } else if (center == ELEMENT.IMG) {
            ivCenter.visibility = View.VISIBLE
            tvTopbarTitle.visibility = View.GONE
        } else {
            flayoutCenter.visibility = View.GONE
        }
        if (right == ELEMENT.TEXT) {
            ivRight.visibility = View.GONE
            tvRight.visibility = View.VISIBLE
        } else if (right == ELEMENT.IMG) {
            ivRight.visibility = View.VISIBLE
            tvRight.visibility = View.GONE
        } else {
            flayoutRight.visibility = View.GONE
        }
    }

    fun setImageRes(resId: Int, aPos: Pos) {
        if (aPos == Pos.LEFT) {
            ivLeft.setImageResource(resId)
        } else if (aPos == Pos.RIGHT) {
            ivRight.setImageResource(resId)
        } else {
            ivCenter.setImageResource(resId)
        }
    }

    fun setTexts(msg: String, aPos: Pos) {
        if (aPos == Pos.LEFT) {
            tvLeft.text = msg
        } else if (aPos == Pos.RIGHT) {
            tvRight.text = msg
        } else {
            tvTopbarTitle.text = msg
        }
    }

    fun setTitle(text: String) {
        tvTopbarTitle.text = text
    }
}
