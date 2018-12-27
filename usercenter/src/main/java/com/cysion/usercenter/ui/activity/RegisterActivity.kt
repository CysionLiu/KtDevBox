package com.cysion.usercenter.ui.activity

import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.ktbox.net.ErrorHandler
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other.color
import com.cysion.targetfun._subscribe
import com.cysion.uibox.bar.TopBar
import com.cysion.uibox.dialog.Alert
import com.cysion.uibox.toast.toast
import com.cysion.usercenter.R
import com.cysion.usercenter.helper.UserCache
import com.cysion.usercenter.net.UserCaller
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {


    override fun getLayoutId(): Int = R.layout.activity_register
    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        topbar.apply {
            setTitle("注册")
            setOnTopBarClickListener { obj, pos ->
                if (pos == TopBar.Pos.LEFT) {
                    finish()
                }
            }
        }
        //简单校验下
        tvLogin.setOnClickListener {
            if (etUsername.text.toString().length <= 3
                || etPwd.text.toString().length <= 3
                || etConfirmPwd.text.toString().length <= 3
            ) {
                toast("长度不符")
                return@setOnClickListener
            }
            if (!etPwd.text.toString().equals(etConfirmPwd.text.toString())) {
                toast("两次密码不一致")
                return@setOnClickListener
            }

            toRegister()
        }
    }

    private fun toRegister() {
        Alert.loading(self)
        UserCaller.api.register(
            etUsername.text.toString().trim(),
            etPwd.text.toString().trim(),
            etConfirmPwd.text.toString()
        ).compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    Alert.close()
                    toast("注册成功")
                    UserCache.save(it)
                    finish()
                }
                _onError {
                    Alert.close()
                    val handle = ErrorHandler.handle(it)
                    toast(handle.errorMsg)
                }
            }
    }

    override fun closeMvp() {
    }

}
