package com.cysion.ktbox.listener
/*
View基类；与presenter绑定使用
 */
interface IView {
    
    //显示加载提示
    fun loading()

    //结束加载提示
    fun stopLoad()

    /*
    code错误码；msg错误信息;
     */
    fun error(code: Int, msg: String)
}