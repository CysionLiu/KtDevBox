package com.cysion.usercenter.entity

import java.io.Serializable

data class Blog(
    val authorId: String,
    val blogId: String,
    val createStamptime: String,
    val icon: String,
    val isCollected: Int,
    val isDeleted: Int,
    val modifyStamptime: String,
    var prideCount: Int,
    val text: String,
    val title: String,
    val isLargeIcon:Int,
    var isPrided:Int
) : Serializable