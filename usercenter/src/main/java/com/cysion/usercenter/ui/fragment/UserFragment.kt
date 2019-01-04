package com.cysion.usercenter.ui.fragment

import android.text.TextUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseFragment
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.other._setOnClickListener
import com.cysion.other.addTo
import com.cysion.other.startActivity_ex
import com.cysion.targetfun._subscribe
import com.cysion.usercenter.R
import com.cysion.usercenter.helper.UserCache
import com.cysion.usercenter.net.UserCaller
import com.cysion.usercenter.ui.activity.CollectActivitiy
import com.cysion.usercenter.ui.activity.LoginActivity
import com.cysion.usercenter.ui.activity.UserBlogActivity
import com.cysion.usercenter.ui.activity.UserDetailActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_user_center.*

class UserFragment : BaseFragment() {


    val compositeDisposable: CompositeDisposable = CompositeDisposable()
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
        rlCollect._setOnClickListener {
            if (TextUtils.isEmpty(UserCache.token)) {
                context.startActivity_ex<LoginActivity>()
            } else {
                context.startActivity_ex<CollectActivitiy>()
            }
        }
        rlBlog._setOnClickListener {
            if (TextUtils.isEmpty(UserCache.token)) {
                context.startActivity_ex<LoginActivity>()
            } else {
                context.startActivity_ex<UserBlogActivity>()
            }
        }
        tvLogout._setOnClickListener {
            UserCache.clear()
            updateUserInfo()
        }
    }

    private fun toLogin() {
        if (TextUtils.isEmpty(UserCache.userId)) {
            context.startActivity_ex<LoginActivity>()
        } else {
            context.startActivity_ex<UserDetailActivity>()
        }
    }

    override fun lazyLoad() {
        super.lazyLoad()
        UserCaller.api.getUserInfo()
            .compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    if (!TextUtils.isEmpty(it.token)) {
                        UserCache.save(it)
                    }
                }
            }.addTo(compositeDisposable)
    }

    override fun visibleAgain() {
        super.visibleAgain()
        updateUserInfo()
    }

    override fun onResume() {
        super.onResume()
        updateUserInfo()
    }

    private fun updateUserInfo() {
        Glide.with(context).load(UserCache.userInfo?.avatar ?: R.mipmap.place_holder)
            .apply(RequestOptions.circleCropTransform())
            .into(ivUserHead)
        tvNickname.text = UserCache.userInfo?.nickname ?: "未登录"
    }

    override fun closeMvp() {
        compositeDisposable.dispose()
    }

}