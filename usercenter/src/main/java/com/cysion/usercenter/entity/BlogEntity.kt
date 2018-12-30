package com.cysion.usercenter.entity

data class Blog(
    val authorId: String,
    val blogId: String,
    val createStamptime: String,
    val icon: String,
    val isCollected: Int,
    val isDeleted: Int,
    val modifyStamptime: String,
    val prideCount: Int,
    val text: String,
    val title: String,
    val isLargeIcon:Int
)