package com.cysion.usercenter.ui.activity

import android.app.Activity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other._setOnClickListener
import com.cysion.other.color
import com.cysion.other.startActivity_ex
import com.cysion.uibox.bar.TopBar
import com.cysion.uibox.dialog.Alert
import com.cysion.uibox.toast.toast
import com.cysion.usercenter.R
import com.cysion.usercenter.constant.*
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.event.BlogEvent
import com.cysion.usercenter.presenter.BlogDetailPresenter
import com.cysion.usercenter.ui.iview.BlogDetailView
import kotlinx.android.synthetic.main.activity_blog_detail.*
import org.greenrobot.eventbus.EventBus

class BlogDetailActivity : BaseActivity(), BlogDetailView {

    /*
    启动该activity
    type=0，创建；1，编辑
     */
    companion object {
        fun start(activity: Activity, blog: Blog) {
            val b = Bundle()
            b.putSerializable(BLOG, blog)
            activity.startActivity_ex<BlogDetailActivity>(BUNDLE_KEY, b)
        }
    }

    private val presenter by lazy {
        BlogDetailPresenter().apply {
            attach(this@BlogDetailActivity)
        }
    }

    private lateinit var blog: Blog
    private var blogId = ""


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
        blogId = blog.blogId
        fillView()
        initEvent()
    }

    private fun initEvent() {
        llCollect._setOnClickListener {
            if (blog.isCollected == 0) {
                presenter.collect(blog.blogId)
            } else {
                presenter.unCollect(blog.blogId)
            }
        }
        llPride._setOnClickListener {
            if (blog.isPrided == 0) {
                presenter.pride(blog)
            } else {
                presenter.unPride(blog)
            }
        }
    }

    private fun fillView() {
        tvBlogTitle.text = blog.title
        tvContent.text = blog.text
        Glide.with(self).load(blog.icon)
            .apply(RequestOptions.placeholderOf(R.mipmap.place_holder_big))
            .into(ivIcon)
        tvPride.text = "${blog.prideCount}"
        ivPride.isSelected = blog.isPrided == 1
        ivCollect.isSelected = blog.isCollected == 1
        tvCollect.text = if (blog.isCollected == 1) "已收藏" else "收藏"
    }

    override fun closeMvp() {
        presenter.detach()
    }

    override fun prideOk(blogId: String) {
        fillView()
        sendBusEvent(PRIDE_OK, blogId)
    }

    override fun unprideOk(blogId: String) {
        fillView()
        sendBusEvent(PRIDE_CANCEL, blogId)
    }

    override fun collectOk(blogId: String) {
        blog.isCollected = 1
        fillView()
        sendBusEvent(COLLECT_OK, blogId)
    }

    override fun unCollectOk(blogId: String) {
        blog.isCollected = 0
        fillView()
        sendBusEvent(COLLECT_CANCEL, blogId)
    }

    override fun commentOk(blogId: String) {
    }

    override fun getComments() {
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

    //发送eventbus事件，用于更新首页列表
    fun sendBusEvent(tag: Int, msg: String) {
        EventBus.getDefault().post(BlogEvent(tag, msg))
    }
}
