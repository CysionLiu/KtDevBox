package com.cysion.usercenter.helper

import com.cysion.usercenter.entity.Blog

object BlogHelper {

    //根据id得到列表中的博客
    fun getBlog(blogid: String, datalist: MutableList<Blog>): Blog? {
        var blog = null
        datalist.forEach {
            if (it.blogId.equals(blogid)) {
                return it
            }
        }
        return null
    }

}