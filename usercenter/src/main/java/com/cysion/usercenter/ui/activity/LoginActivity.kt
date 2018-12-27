package com.cysion.usercenter.ui.activity

import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other._setOnClickListener
import com.cysion.other.color
import com.cysion.other.startActivity_ex
import com.cysion.uibox.bar.TopBar
import com.cysion.uibox.dialog.Alert
import com.cysion.uibox.toast.toast
import com.cysion.usercenter.R
import com.cysion.usercenter.entity.UserEntity
import com.cysion.usercenter.presenter.LoginPresenter
import com.cysion.usercenter.ui.iview.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    val presenter by lazy {
        LoginPresenter().apply {
            attach(this@LoginActivity)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        topbar.apply {
            setTitle("登录")
            setOnTopBarClickListener { obj, pos ->
                if (pos == TopBar.Pos.LEFT) {
                    finish()
                }
            }
        }

        //简单校验下
        tvLogin.setOnClickListener {
            if (etUsername.text.toString().length <= 3 || etPwd.text.toString().length <= 3) {
                toast("长度不符")
                return@setOnClickListener
            }
            presenter.login(etUsername.text.toString(), etPwd.text.toString())
        }

        tvRegister._setOnClickListener {
            self.startActivity_ex<RegisterActivity>()
            finish()
        }
    }

    override fun setUserInfo(u: UserEntity) {
        toast("登录成功")
        finish()
    }

    override fun loading() {
        Alert.loading(self)
    }

    override fun stopLoad() {
        Alert.close()
    }

    override fun error(code: Int, msg: String) {
        toast(msg)
    }

    override fun closeMvp() {
        presenter.detach()
    }
}
