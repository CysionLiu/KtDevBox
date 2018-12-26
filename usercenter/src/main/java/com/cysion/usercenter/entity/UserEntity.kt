package com.cysion.usercenter.entity

data class UserEntity(
    val avatar: String,
    val createStamptime: String,
    val modifyStamptime: String,
    val name: String,
    val nickname: String,
    val selfDesc: String,
    val token: String,
    val userId: String
)