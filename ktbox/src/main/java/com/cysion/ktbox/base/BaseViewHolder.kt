package com.cysion.ktbox.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cysion.ktbox.listener.OnTypeClickListener

abstract class BaseViewHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mPageType: String? = null
    protected lateinit var mOnTypeClickListener: OnTypeClickListener
    protected lateinit var mContext: Context

    fun bindData(aContext: Context, aOnTypeClickListener: OnTypeClickListener, obj: T, position: Int) {
        mOnTypeClickListener = aOnTypeClickListener
        mContext = aContext
        if (mOnTypeClickListener == null) {
            mOnTypeClickListener = object : OnTypeClickListener {
                override fun onTypeClicked(obj: Any, position: Int, flag: Int) {
                }
            }
        }
        itemView.setOnClickListener {
            mOnTypeClickListener.onTypeClicked(obj, position, ITEM_CLICK)
            fillData(obj, position)
        }
    }

    protected abstract fun fillData(obj: T, position: Int)
}