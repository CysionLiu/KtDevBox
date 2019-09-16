package com.cysion.usercenter.entity

data class DetailUserEntity(
    val avatar: String,
    val blogs: List<Blog>,
    val createStamptime: String,
    val modifyStamptime: String,
    val name: String,
    val nickname: String,
    val selfDesc: String,
    val userId: String
)