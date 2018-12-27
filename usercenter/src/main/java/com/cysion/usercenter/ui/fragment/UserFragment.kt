package com.cysion.usercenter.ui.fragment

import android.text.TextUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseFragment
import com.cysion.other._setOnClickListener
import com.cysion.other.startActivity_ex
import com.cysion.usercenter.R
import com.cysion.usercenter.helper.UserCache
import com.cysion.usercenter.ui.activity.LoginActivity
import com.cysion.usercenter.ui.activity.UserDetailActivity
import kotlinx.android.synthetic.main.fragment_user_center.*

class UserFragment : BaseFragment() {


    override fun getLayoutId(): Int = R.layout.fragment_user_center

    override fun initView() {
        Glide.with(context).load(R.mipmap.place_holder)
            .apply(RequestOptions.circleCropTransform())
            .into(ivUserHead)
        ivUserHead._setOnClickListener {
            toLogin()
        }
        rlInfo._setOnClickListener {
            toLogin()
        }
    }

    private fun toLogin() {
        if (TextUtils.isEmpty(UserCache.userId)) {
            context.startActivity_ex<LoginActivity>()
        } else {
            context.startActivity_ex<UserDetailActivity>()
        }
    }

    override fun visibleAgain() {
        super.visibleAgain()

    }

    override fun onResume() {
        super.onResume()
        UserCache.userInfo?.apply {
            Glide.with(context).load(UserCache.userInfo?.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(ivUserHead)
            tvNickname.text = UserCache.userInfo?.nickname
        }
    }

    override fun closeMvp() {

    }

}