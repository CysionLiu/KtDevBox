package com.cysion.usercenter.helper

import com.cysion.ktbox.Box
import com.cysion.ktbox.utils.ACache
import com.cysion.ktbox.utils.fromjson
import com.cysion.usercenter.entity.UserEntity
import com.google.gson.Gson

object UserCache {

    const val USER_KEY = "user_key"
    var userId: String = ""
    var token: String = ""
    var userInfo: UserEntity? = null

    fun save(u: UserEntity?) {
        if (u == null) {
            return
        }
        userInfo = u
        userId = u.userId
        token = u.token
        ACache.get(Box.context).put(USER_KEY, Gson().toJson(userInfo))
    }

    fun clear() {
        userInfo?.apply {
            userInfo = null
            UserCache.token = ""
            UserCache.userId = ""
            ACache.get(Box.context).put(USER_KEY, "")
        }
    }

    fun fromCache() {
        val userinfo = ACache.get(Box.context).getAsString(USER_KEY)
        var userInfo = Gson().fromjson<UserEntity>(userinfo)
        save(userInfo)
    }
}