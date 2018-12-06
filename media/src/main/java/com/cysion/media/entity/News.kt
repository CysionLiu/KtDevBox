package com.cysion.media.entity


data class Data(
    val tech: List<NewsInfo>?,
    val auto: List<NewsInfo>?,
    val money: List<NewsInfo>?,
    val sports: List<NewsInfo>?, val dy: List<NewsInfo>?
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