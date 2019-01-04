package com.cysion.usercenter.entity

data class CollectEntity(
    val authorId: String,
    val colType: Int,
    val coverImg: String,
    val createStamptime: String,
    val itemId: String,
    val itemTitle: String,
    val linkUrl: String,
    val modifyStamptime: String,
    val isLargeIcon:Int
)