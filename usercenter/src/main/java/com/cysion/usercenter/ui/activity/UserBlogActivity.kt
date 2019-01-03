package com.cysion.usercenter.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.base.ITEM_CLICK
import com.cysion.ktbox.net.ErrorStatus
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other.color
import com.cysion.uibox.bar.TopBar
import com.cysion.uibox.dialog.Alert
import com.cysion.uibox.dialog.CONFIRM
import com.cysion.uibox.toast.toast
import com.cysion.usercenter.R
import com.cysion.usercenter.adapter.BlogAdapter
import com.cysion.usercenter.adapter.BlogAdapter.Companion.DEL
import com.cysion.usercenter.adapter.BlogAdapter.Companion.EDIT
import com.cysion.usercenter.adapter.BlogAdapter.Companion.PRIDE
import com.cysion.usercenter.adapter.BlogAdapter.Companion.USER_PAGE
import com.cysion.usercenter.constant.COLLECT_CANCEL
import com.cysion.usercenter.constant.COLLECT_OK
import com.cysion.usercenter.constant.PRIDE_CANCEL
import com.cysion.usercenter.constant.PRIDE_OK
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.event.BlogEvent
import com.cysion.usercenter.helper.BlogHelper
import com.cysion.usercenter.presenter.UserBlogPresenter
import com.cysion.usercenter.ui.iview.UserBlogListView
import kotlinx.android.synthetic.main.activity_user_blogs.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

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
        initRV()
    }

    private fun initRV() {
        rvBloglist.layoutManager = LinearLayoutManager(self)
        blogAdapter = BlogAdapter(mBlogs, self, USER_PAGE)
        rvBloglist.adapter = blogAdapter
        blogAdapter.setOnTypeClickListener { obj, position, flag ->
            if (flag == ITEM_CLICK) {
                BlogDetailActivity.start(self, obj)
            } else if (flag == PRIDE) {
//                if (obj.isPrided == 1) {
//                    unPride(obj, position)
//                } else {
//                    toPride(obj, position)
//                }
            } else if (flag == EDIT) {
                BlogEditorActivity.start(self, obj.title, obj.text, 1, obj.blogId)
            } else if (flag == DEL) {
                Alert.normal(self, "提示", "确认删除这个博客吗？") { type, msg ->
                    if (type == CONFIRM) {
                        presenter.deleteBlog(obj.blogId)
                    }
                }
            }
        }
    }


    override fun initData() {
        super.initData()
        presenter.getBlogs()
    }

    override fun setList(blogList: MutableList<Blog>) {
        mBlogs.clear()
        mBlogs.addAll(blogList)
        blogAdapter.notifyDataSetChanged()
        if (mBlogs.size == 0) {
            multiView.showEmpty()
        }
    }

    override fun delSuccessful() {
        toast("删除博客成功")
        presenter.getBlogs()
    }


    private fun toPride(blog: Blog, pos: Int) {
        presenter.pride(blog, pos)
    }

    override fun prideOk(index: Int) {
        blogAdapter.notifyItemChanged(index)

    }

    private fun unPride(blog: Blog, pos: Int) {
        presenter.unPride(blog, pos)
    }


    override fun unprideOk(index: Int) {
        blogAdapter.notifyItemChanged(index)
    }

    override fun loading() {
        Alert.loading(self)
    }

    override fun stopLoad() {
        Alert.close()
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
    //接收BlogEvent事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receive(event: BlogEvent) {
        when (event.tag) {
            PRIDE_OK ->
                BlogHelper.getBlog(event.msg, mBlogs)?.apply {
                    isPrided = 1
                    prideCount++
                }
            PRIDE_CANCEL ->
                BlogHelper.getBlog(event.msg, mBlogs)?.apply {
                    isPrided = 0
                    prideCount--
                }
            COLLECT_OK ->
                BlogHelper.getBlog(event.msg, mBlogs)?.isCollected = 1
            COLLECT_CANCEL ->
                BlogHelper.getBlog(event.msg, mBlogs)?.isCollected = 0
        }
        blogAdapter.notifyDataSetChanged()

    }
}
