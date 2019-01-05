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
import com.cysion.usercenter.constant.COLLECT_CANCEL
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.entity.CollectEntity
import com.cysion.usercenter.event.BlogEvent
import com.cysion.usercenter.presenter.CollectPresenter
import com.cysion.usercenter.ui.iview.CollectView
import kotlinx.android.synthetic.main.activity_user_blogs.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CollectActivitiy : BaseActivity(), CollectView {

    val presenter by lazy {
        CollectPresenter().apply {
            attach(this@CollectActivitiy)
        }
    }

    private lateinit var blogAdapter: BlogAdapter
    private val mBlogs: MutableList<Blog> = mutableListOf()

    override fun getLayoutId(): Int = R.layout.activity_user_blogs

    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        topbar.apply {
            setTitle("我的收藏")
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
        blogAdapter = BlogAdapter(mBlogs, self, BlogAdapter.COLLECT)
        rvBloglist.adapter = blogAdapter
        blogAdapter.setOnTypeClickListener { obj, position, flag ->
            if (flag == ITEM_CLICK) {
                BlogDetailActivity.start(self, null,obj.blogId)
            } else if (flag == BlogAdapter.DEL) {
                Alert.normal(self, "提示", "确认取消这个收藏吗？") { type, msg ->
                    if (type == CONFIRM) {
                        presenter.delCol(obj.blogId)
                    }
                }
            }
        }
    }

    override fun initData() {
        super.initData()
        presenter.getBlogs()
    }


    override fun setList(dataList: MutableList<CollectEntity>) {
        mBlogs.clear()
        dataList.forEach {
            val tmp = Blog(
                it.authorId, it.itemId, "", it.coverImg,
                1, 0, "",
                0, 0,"",it.itemTitle,  it.isLargeIcon, 0
            )
            mBlogs.add(tmp)
        }
        blogAdapter.notifyDataSetChanged()
        if (mBlogs.size == 0) {
            multiView.showEmpty()
        }
    }

    override fun onCanceled() {
        initData()
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
            COLLECT_CANCEL ->
                initData()
        }
        blogAdapter.notifyDataSetChanged()
    }
}
