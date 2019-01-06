package com.cysion.ktbox.listener
/*
//处理列表交互事件，obj，泛型，列表实体对象；position，条目所在位置；
flag:事件类型，单击，长按，分享，评论，赞，收藏等点击事件
 */
typealias OnTypeClickListener<T> = (obj: T, position: Int, flag: Int) -> Unit
