package com.cysion.usercenter.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other._setOnClickListener
import com.cysion.other.color
import com.cysion.other.startActivity_ex
import com.cysion.uibox.bar.TopBar
import com.cysion.uibox.dialog.Alert
import com.cysion.uibox.dialog.CONFIRM
import com.cysion.uibox.toast.toast
import com.cysion.usercenter.R
import com.cysion.usercenter.adapter.CommentAdapter
import com.cysion.usercenter.constant.*
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.entity.CommentEntity
import com.cysion.usercenter.event.BlogEvent
import com.cysion.usercenter.helper.BlogHelper
import com.cysion.usercenter.presenter.BlogDetailPresenter
import com.cysion.usercenter.ui.iview.BlogDetailView
import kotlinx.android.synthetic.main.activity_blog_detail.*
import org.greenrobot.eventbus.EventBus

class BlogDetailActivity : BaseActivity(), BlogDetailView {


    companion object {
        //blog和blogId不能同时为空
        fun start(activity: Activity, blog: Blog?, blogId: String = "") {
            if (blog == null && TextUtils.isEmpty(blogId)) {
                return
            }
            val b = Bundle()
            b.putString(BLOG_ID, blogId)
            b.putSerializable(BLOG, blog)
            activity.startActivity_ex<BlogDetailActivity>(BUNDLE_KEY, b)
        }
    }

    //评论相关
    private val commentList = mutableListOf<CommentEntity>()
    private lateinit var commentAdapter: CommentAdapter


    private var blog: Blog? = null
    private var mBlogId = ""

    private val presenter by lazy {
        BlogDetailPresenter().apply {
            attach(this@BlogDetailActivity)
        }
    }

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
        llAuthorArea._setOnClickListener {
            PeopleInfoActivity.start(self, blog?.authorId!!)
        }
        initCommentView()
    }

    //初始化评论列表
    private fun initCommentView() {
        rvCommentlist.isNestedScrollingEnabled = false
        rvCommentlist.layoutManager = LinearLayoutManager(self)
        commentAdapter = CommentAdapter(commentList, self)
        rvCommentlist.adapter = commentAdapter
    }

    override fun initData() {
        super.initData()
        val bundleExtra = intent.getBundleExtra(BUNDLE_KEY)
        val tmp = bundleExtra.getSerializable(BLOG)
        if (tmp != null) {
            blog = tmp as Blog
        }
        mBlogId = bundleExtra.getString(BLOG_ID)
        //若传来空id，则blog必然不能为空
        if (TextUtils.isEmpty(mBlogId)) {
            mBlogId = blog?.blogId!!
        } else {
            getDetail()
        }
        fillView()
        initEvent()
        presenter.getComments(mBlogId)
    }

    private fun fillView() {
        blog?.apply {
            tvBlogTitle.text = title
            tvContent.text = text
            tvAuthorName.text = authorName

            Glide.with(self).load(authorAvatar)
                .apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.place_holder))
                .into(ivAvatar)
            Glide.with(self).load(icon)
                .apply(RequestOptions.placeholderOf(R.mipmap.place_holder_big))
                .into(ivIcon)
            tvPride.text = "${prideCount}"
            ivPride.isSelected = isPrided == 1
            ivCollect.isSelected = isCollected == 1
            tvCollect.text = if (isCollected == 1) "已收藏" else "收藏"
            if (TextUtils.isEmpty(createStamptime)) {
                llPride.visibility = View.GONE
            }
        }

    }

    private fun initEvent() {
        llCollect._setOnClickListener {
            blog?.apply {
                if (isCollected == 0) {
                    presenter.collect(mBlogId)
                } else {
                    presenter.unCollect(mBlogId)
                }
            }

        }
        llPride._setOnClickListener {
            blog?.apply {
                if (isPrided == 0) {
                    presenter.pride(this)
                } else {
                    presenter.unPride(this)
                }
            }

        }
        llComment._setOnClickListener {
            BlogHelper.comment(self) { type: Int, msg: String ->
                if (type == CONFIRM) {
                    presenter.comment(mBlogId, msg)
                }
            }
        }

    }

    //根据id请求博客
    private fun getDetail() {
        presenter.getBlog(mBlogId)
    }

    //根据id获得了博客内容
    override fun onGetBlog(obj: Blog) {
        blog = obj
        fillView()
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
        blog?.isCollected = 1
        fillView()
        sendBusEvent(COLLECT_OK, blogId)
    }

    override fun unCollectOk(blogId: String) {
        blog?.isCollected = 0
        fillView()
        sendBusEvent(COLLECT_CANCEL, blogId)
    }

    override fun commentOk(blogId: String) {
        toast("评论成功")
        presenter.getComments(blogId)
        sendBusEvent(COMMENT, blogId)
    }

    override fun onGetComments(datalist: MutableList<CommentEntity>) {
        commentList.clear()
        commentList.addAll(datalist)
        commentAdapter?.notifyDataSetChanged()
        if (datalist.size == 0) {
            multiView.showEmpty()
        } else {
            multiView.showContent()
        }
    }

    override fun loading() {
        Alert.loading(self)
    }

    override fun stopLoad() {
        Alert.close()
    }

    override fun error(code: Int, msg: String) {
        toast(msg)
        if (commentList.size == 0) {
            multiView.showEmpty()
        }
    }

    override fun closeMvp() {
        presenter.detach()
    }

    //发送eventbus事件，用于更新首页列表
    fun sendBusEvent(tag: Int, msg: String) {
        EventBus.getDefault().post(BlogEvent(tag, msg))
    }
}
