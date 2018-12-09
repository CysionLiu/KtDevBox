package com.cysion.media.entity


data class Data(
    val tech: MutableList<NewsInfo>?,
    val auto: MutableList<NewsInfo>?,
    val money: MutableList<NewsInfo>?,
    val sports: MutableList<NewsInfo>?, val dy: MutableList<NewsInfo>?
)

data class NewsInfo(
    val addata: String,
    val category: String,
    val channel: String,
    val digest: String,
    val docid: String,
    val imgsrc3gtype: Int,
    val imgsrcFrom: String,
    val isTop: String,
    val link: String?,
    val liveInfo: Any?,
    val picInfo: List<PicInfo>?,
    val ptime: String?,
    val source: String?,
    val tag: String?,
    val tcount: Int?,
    val title: String?,
    val type: String?,
    val typeid: String?,
    val unlikeReason: String?,
    val videoInfo: String?
)

data class PicInfo(
    val height: Int?,
    val ref: String?,
    val url: String?,
    val width: Int?
)