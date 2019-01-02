package com.cysion.usercenter.ui.activity

import android.app.Activity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other.color
import com.cysion.other.startActivity_ex
import com.cysion.uibox.bar.TopBar
import com.cysion.usercenter.R
import com.cysion.usercenter.constant.BLOG
import com.cysion.usercenter.constant.BLOG_INDEX
import com.cysion.usercenter.constant.BUNDLE_KEY
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.presenter.BlogDetailPresenter
import com.cysion.usercenter.ui.iview.BlogDetailView
import kotlinx.android.synthetic.main.activity_blog_detail.*

class BlogDetailActivity : BaseActivity(), BlogDetailView {

    /*
    启动该activity
    type=0，创建；1，编辑
     */
    companion object {
        fun start(activity: Activity, blog: Blog, index: Int) {
            val b = Bundle()
            b.putSerializable(BLOG, blog)
            b.putSerializable(BLOG_INDEX, index)
            activity.startActivity_ex<BlogDetailActivity>(BUNDLE_KEY, b)
        }
    }

    private val presenter by lazy {
        BlogDetailPresenter().apply {
            attach(this@BlogDetailActivity)
        }
    }

    private lateinit var blog: Blog
    private var blogIndex = 0


    override fun getLayoutId(): Int = R.layout.activity_blog_detail

    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        topbar.apply {
            setTitle("详情")
            setOnTopBarClickListener { obj, pos ->
                if (pos == TopBar.Pos.LEFT) {
                    finish()
                }
            }
        }
    }

    override fun initData() {
        super.initData()
        val bundleExtra = intent.getBundleExtra(BUNDLE_KEY)
        blog = bundleExtra.getSerializable(BLOG) as Blog
        blogIndex = bundleExtra.getInt(BLOG_INDEX)
        fillView()
    }

    private fun fillView() {
        tvBlogTitle.text = blog.title
        tvContent.text = blog.text
        Glide.with(self).load(blog.icon)
            .apply(RequestOptions.placeholderOf(R.mipmap.place_holder_big))
            .into(ivIcon)
        tvPride.text = "${blog.prideCount}"
        ivPride.isSelected = blog.isPrided == 1
    }

    override fun closeMvp() {
    }

    override fun prideOk(index: Int) {
    }

    override fun unprideOk(index: Int) {
    }

    override fun collect(index: Int) {
    }

    override fun unCollect(index: Int) {
    }

    override fun comment() {
    }

    override fun getComments() {
    }

    override fun loading() {
    }

    override fun stopLoad() {
    }

    override fun error(code: Int, msg: String) {
    }
}
