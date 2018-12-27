package com.cysion.usercenter.ui.activity

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other.color
import com.cysion.uibox.bar.TopBar
import com.cysion.usercenter.R
import com.cysion.usercenter.helper.UserCache
import kotlinx.android.synthetic.main.activity_userdetail.*

class UserDetailActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_userdetail

    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        topbar.apply {
            setTitle("用户信息")
            setOnTopBarClickListener { obj, pos ->
                if (pos == TopBar.Pos.LEFT) {
                    finish()
                }
            }
        }
        Glide.with(self).load(UserCache.userInfo?.avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(ivAvatar)
        tvUsername.text = UserCache.userInfo?.name
        etNickname.setText(UserCache.userInfo?.nickname ?: "")
        etDesc.setText(UserCache.userInfo?.selfDesc ?: "")
    }

    override fun closeMvp() {
    }
}
