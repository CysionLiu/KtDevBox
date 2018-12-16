package com.cysion.media.entity

data class SongList(
    val artistid: Any,
    val avatar: Any,
    val ch_name: String,
    val channel: String,
    val count: Any,
    val songlist: MutableList<Song>
)

data class Song(
    val all_artist_id: String,
    val all_rate: String,
    val artist: String,
    val artist_id: String,
    val charge: Int,
    val flow: Int,
    val havehigh: Int,
    val method: Int,
    val resource_type: String,
    val songid: String,
    val thumb: String,
    val title: String
)