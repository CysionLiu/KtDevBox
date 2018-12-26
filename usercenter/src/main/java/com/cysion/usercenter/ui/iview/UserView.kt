package com.cysion.usercenter.ui.iview

import com.cysion.ktbox.listener.IView
import com.cysion.usercenter.entity.UserEntity

interface LoginView:IView{
    fun setUserInfo(u:UserEntity)
}