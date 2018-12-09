package com.cysion.ktbox.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cysion.ktbox.listener.OnTypeClickListener

abstract class BaseAdapter<T : Any>(
    var mEntities: MutableList<T>, var mContext: Context
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var mOnTypeClickListener: OnTypeClickListener? = null
    override fun getItemCount(): Int {
        return mEntities.size
    }

    fun setOnTypeClickListener(aListener: OnTypeClickListener) {
        mOnTypeClickListener = aListener

    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(mContext, mOnTypeClickListener, mEntities[position], position)
    }

    fun addEntities(aEntities: MutableList<T>) {
        mEntities.addAll(aEntities)
        notifyDataSetChanged()
    }

    fun setEntities(aEntities: MutableList<T>) {
        mEntities.clear()
        mEntities.addAll(aEntities)
        notifyDataSetChanged()
    }

    override abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>
}

//条目单击事件，
val ITEM_CLICK = -100000
