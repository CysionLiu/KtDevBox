package com.cysion.usercenter.entity

data class CommentEntity(
    val authorAvatar: String,
    val authorId: String,
    val authorName: String,
    val content: String,
    val createStamptime: String,
    val modifyStamptime: String,
    val parentId: String,
    val type: Int
)