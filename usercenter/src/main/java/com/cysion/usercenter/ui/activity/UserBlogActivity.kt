package com.cysion.usercenter.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.net.ErrorStatus
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other.color
import com.cysion.uibox.bar.TopBar
import com.cysion.uibox.toast.toast
import com.cysion.usercenter.R
import com.cysion.usercenter.adapter.BlogAdapter
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.presenter.UserBlogPresenter
import com.cysion.usercenter.ui.iview.UserBlogListView
import kotlinx.android.synthetic.main.activity_user_blogs.*

class UserBlogActivity : BaseActivity(), UserBlogListView {

    val presenter by lazy {
        UserBlogPresenter().apply {
            attach(this@UserBlogActivity)
        }
    }

    private var curPage = 1
    private lateinit var blogAdapter: BlogAdapter
    private val mBlogs: MutableList<Blog> = mutableListOf()

    override fun getLayoutId(): Int = R.layout.activity_user_blogs

    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        topbar.apply {
            setTitle("我的博客")
            setOnTopBarClickListener { obj, pos ->
                if (pos == TopBar.Pos.LEFT) {
                    finish()
                }
            }
        }
        rvBloglist.layoutManager = LinearLayoutManager(self)
        blogAdapter = BlogAdapter(mBlogs, self)
        rvBloglist.adapter = blogAdapter
    }

    override fun initData() {
        super.initData()
        presenter.getBlogs()
    }

    override fun setList(blogList: MutableList<Blog>) {
        mBlogs.addAll(blogList)
        blogAdapter.notifyDataSetChanged()
    }

    override fun delSuccessful() {
    }

    override fun loading() {
        multiView.showLoading()
    }

    override fun stopLoad() {
        multiView.showContent()
    }

    override fun error(code: Int, msg: String) {
        toast(msg)
        if (mBlogs.size == 0) {
            multiView.showEmpty()
            if (code == ErrorStatus.NETWORK_ERROR) {
                multiView.showNoNetwork()
            }
        }
    }

    override fun closeMvp() {
        presenter.detach()
    }
}
