package com.cysion.ktbox.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cysion.ktbox.listener.OnTypeClickListener

abstract class BaseAdapter<T : Any>(
    var mEntities: MutableList<T>, var mContext: Context
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    //处理交互事件
    var mOnTypeClickListener: OnTypeClickListener<T>? = null
    override fun getItemCount(): Int {
        return mEntities.size
    }

    fun setOnTypeClickListener(aListener: OnTypeClickListener<T>) {
        mOnTypeClickListener = aListener

    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(mContext, mOnTypeClickListener, mEntities[position], position)
    }

    override abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>
}

//条目单击事件，
const val ITEM_CLICK = -100000
//长按事件
const val ITEM_LONG_CLICK = -100001
