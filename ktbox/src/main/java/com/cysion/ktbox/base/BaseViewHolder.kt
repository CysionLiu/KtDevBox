package com.cysion.ktbox.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cysion.ktbox.listener.OnTypeClickListener

abstract class BaseViewHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mPageType: String? = null
    protected var mOnTypeClickListener: OnTypeClickListener? = null
    protected lateinit var mContext: Context

    fun bindData(aContext: Context, aOnTypeClickListener: OnTypeClickListener?, obj: T, position: Int) {
        mOnTypeClickListener = aOnTypeClickListener
        mContext = aContext
        itemView.setOnClickListener {
            mOnTypeClickListener?.invoke(obj, position, ITEM_CLICK)
        }
        itemView.setOnLongClickListener {
            mOnTypeClickListener?.invoke(obj, position, ITEM_LONG_CLICK)
            true
        }
        fillData(obj, position)
    }

    protected abstract fun fillData(obj: T, position: Int)
}