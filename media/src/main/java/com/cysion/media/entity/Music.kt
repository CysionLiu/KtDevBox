package com.cysion.media.entity


data class ChannelList(val channellist:MutableList<ChannelInfo>)
data class ChannelInfo(
    val cate_name: String,
    val cate_sname: String,
    val ch_name: String,
    val channelid: String,
    val name: String,
    val thumb: String,
    val value: Int
)