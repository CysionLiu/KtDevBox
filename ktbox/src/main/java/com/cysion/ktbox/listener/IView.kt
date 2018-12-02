package com.cysion.ktbox.listener

interface IView {
    
    //显示加载提示
    fun loading()

    //结束加载提示
    fun stopLoad()

    /*
    code错误码；msg错误信息;event事件
     */
    fun error(code: Int, msg: String, event: Int)
}