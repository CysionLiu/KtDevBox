package com.cysion.usercenter.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.net.BaseResponseRx
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other._setOnClickListener
import com.cysion.other.color
import com.cysion.other.startActivity_ex
import com.cysion.targetfun._subscribe
import com.cysion.uibox.dialog.Alert
import com.cysion.usercenter.R
import com.cysion.usercenter.adapter.BlogAdapter
import com.cysion.usercenter.constant.BUNDLE_KEY
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.entity.DetailUserEntity
import com.cysion.usercenter.net.UserCaller
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_people_info.*
import okhttp3.MediaType
import okhttp3.RequestBody

class PeopleInfoActivity : BaseActivity() {

    private lateinit var mUserId: String
    private lateinit var disposable: Disposable
    private var mPeopleInfo: DetailUserEntity? = null
    private lateinit var blogAdapter: BlogAdapter
    private val mBlogs: MutableList<Blog> = mutableListOf()

    companion object {
        const val USER_ID = "user_id"
        //blog和blogId不能同时为空
        fun start(activity: Activity, userId: String = "") {
            if (TextUtils.isEmpty(userId)) {
                return
            }
            val b = Bundle()
            b.putString(USER_ID, userId)
            activity.startActivity_ex<PeopleInfoActivity>(BUNDLE_KEY, b)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_people_info

    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        ivClose._setOnClickListener {
            finish()
        }
        rvBloglist.layoutManager = LinearLayoutManager(self)
        blogAdapter = BlogAdapter(mBlogs, self, BlogAdapter.COMMON_PAGE)
        rvBloglist.adapter = blogAdapter
    }

    override fun initData() {
        super.initData()
        val bundleExtra = intent.getBundleExtra(BUNDLE_KEY)
        mUserId = bundleExtra.getString(USER_ID)
        val json = "{\"userid\":\"${mUserId}\"}"
        val body = RequestBody.create(MediaType.parse("application/json"), json)
        Alert.loading(self)
        disposable = UserCaller.api.getPeopleInfo(body)
            .compose(BaseResponseRx.validateToMain())
            ._subscribe {
                _onNext {
                    mPeopleInfo = it
                    fillView()
                    Alert.close()
                }
                _onError {
                    multiView.showError()
                    Alert.close()
                }
            }
    }

    fun fillView() {
        mPeopleInfo?.apply {
            tvNickname.text = nickname
            tvDesc.text = selfDesc
            mBlogs.addAll(blogs)
            blogAdapter.notifyDataSetChanged()
            Glide.with(self).load(avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(ivUserHead)
        }

    }

    override fun closeMvp() {
        disposable?.dispose()
    }

}
