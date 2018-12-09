package com.cysion.ktbox.net

data class ApiException(val errorCode: Int, val errorMsg: String):RuntimeException()