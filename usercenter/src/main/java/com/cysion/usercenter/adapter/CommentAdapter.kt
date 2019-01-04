package com.cysion.usercenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseAdapter
import com.cysion.ktbox.base.BaseViewHolder
import com.cysion.usercenter.R
import com.cysion.usercenter.entity.CommentEntity
import kotlinx.android.synthetic.main.item_blog_comment.view.*

class CommentAdapter(mEntities: MutableList<CommentEntity>, mContext: Context) :
    BaseAdapter<CommentEntity>(mEntities, mContext) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CommentEntity> {
        return InnerCommentHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.item_blog_comment, parent, false)
        )
    }
}

class InnerCommentHolder(itemView: View) : BaseViewHolder<CommentEntity>(itemView) {
    override fun fillData(obj: CommentEntity, position: Int) {
        obj?.apply {
            Glide.with(mContext).load(obj.authorAvatar)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.ivAuthorAvatar)
            itemView.tvNickname.text = authorName
            itemView.tvContent.text = content
            itemView.tvTime.text = createStamptime
        }
    }
}