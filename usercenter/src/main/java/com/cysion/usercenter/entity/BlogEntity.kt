package com.cysion.usercenter.entity

import java.io.Serializable

data class Blog(
    val authorId: String,
    val blogId: String,
    val createStamptime: String,
    val icon: String,
    var isCollected: Int,
    val isDeleted: Int,
    val modifyStamptime: String,
    var prideCount: Int,
    var commentCount: Int,
    var text: String,
    var title: String,
    val isLargeIcon:Int,
    var isPrided:Int
) : Serializable