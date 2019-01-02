package com.cysion.usercenter.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import com.cysion.ktbox.base.BaseFragment
import com.cysion.ktbox.net.ErrorStatus
import com.cysion.other._setOnClickListener
import com.cysion.other.dp2px
import com.cysion.other.startActivity_ex
import com.cysion.uibox.toast.toast
import com.cysion.usercenter.R
import com.cysion.usercenter.adapter.BlogAdapter
import com.cysion.usercenter.adapter.HomeTopPageAdapter
import com.cysion.usercenter.comm.Resolver.mediaActivityApi
import com.cysion.usercenter.entity.Blog
import com.cysion.usercenter.entity.Carousel
import com.cysion.usercenter.helper.UserCache
import com.cysion.usercenter.presenter.SquarePresenter
import com.cysion.usercenter.ui.activity.BlogEditorActivity
import com.cysion.usercenter.ui.activity.LoginActivity
import com.cysion.usercenter.ui.iview.SquareView
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.tmall.ultraviewpager.UltraViewPager
import kotlinx.android.synthetic.main.fragment_square.*


class SquareFragment : BaseFragment(), SquareView {

    private val presenter by lazy {
        SquarePresenter().apply {
            attach(this@SquareFragment)
        }
    }
    private lateinit var topAdapter: HomeTopPageAdapter
    private lateinit var blogAdapter: BlogAdapter
    private val mCarousels: MutableList<Carousel> = mutableListOf()
    private val mBlogs: MutableList<Blog> = mutableListOf()
    private var curPage = 1

    override fun getLayoutId(): Int = R.layout.fragment_square

    override fun initView() {
        initRefreshLayout()
        initViewPager()
        initRecyclerView()
        initFab()
    }

    private fun initRefreshLayout() {
        smartLayout.setOnRefreshListener {
            curPage = 1
            presenter.getCarousel()
            presenter.getBlogs(curPage)
            smartLayout.setEnableLoadMore(true)
        }
        smartLayout.setOnLoadMoreListener {
            presenter.getBlogs(curPage)
        }
    }

    private fun initViewPager() {
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)
        topAdapter = HomeTopPageAdapter(context, mCarousels)
        ultraViewPager.adapter = topAdapter
        ultraViewPager.initIndicator()
        ultraViewPager.getIndicator()
            .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
            .setFocusColor(Color.RED)
            .setNormalColor(Color.WHITE)
            .setRadius(context.dp2px(3).toInt())
        ultraViewPager.apply {
            indicator.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
                .setMargin(0, 0, 0, context.dp2px(10).toInt())
                .build()
            setInfiniteLoop(true)
            ultraViewPager.setAutoScroll(3000)
        }

        topAdapter.setItemClickListener {
            if (it.type.equals("news")) {
                mediaActivityApi.startNewsActivity(context, it.title, it.link)
            } else if (it.type.equals("music")) {
                mediaActivityApi.startSongsActivity(context, it.title, it.mediaId)
            }
        }
    }

    private fun initRecyclerView() {
        rvBloglist.isNestedScrollingEnabled = false
        blogAdapter = BlogAdapter(mBlogs, context)
        rvBloglist.adapter = blogAdapter
        rvBloglist.layoutManager = LinearLayoutManager(context)
    }


    private fun initFab() {
        fabBtn._setOnClickListener {
            if (TextUtils.isEmpty(UserCache.token)) {
                context.startActivity_ex<LoginActivity>()
            } else {
                BlogEditorActivity.start(context, "", "")
            }
        }
    }

    override fun initData() {
        super.initData()
        smartLayout.autoRefresh()
    }

    override fun setCarousels(carousels: MutableList<Carousel>) {
        mCarousels.clear()
        mCarousels.addAll(carousels)
        ultraViewPager.refresh()
    }

    override fun setBlogList(blogs: MutableList<Blog>) {
        if (curPage == 1) {
            mBlogs.clear()
        }
        curPage++
        mBlogs.addAll(blogs)
        blogAdapter.notifyDataSetChanged()
        multiView.showContent()
    }

    override fun loading() {

    }

    override fun stopLoad() {
        if (smartLayout.state == RefreshState.Refreshing) {
            smartLayout.finishRefresh()
        } else if (smartLayout.state == RefreshState.Loading) {
            smartLayout.finishLoadMore()
        }
    }

    override fun error(code: Int, msg: String) {
        toast(msg)
        if (mBlogs.size == 0 && mCarousels.size == 0) {
            multiView.showEmpty()
            if (code == ErrorStatus.NETWORK_ERROR) {
                multiView.showNoNetwork()
            }
        }
        smartLayout.setEnableLoadMore(false)
    }

    override fun closeMvp() {
        presenter.detach()
    }
}
