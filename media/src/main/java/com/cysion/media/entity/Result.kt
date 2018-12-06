package com.cysion.media.entity

data class Result<T>(val code: Int, val msg: String, val data: T)