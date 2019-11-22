package com.cysion.media.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.cysion.ktbox.base.BaseFragment
import com.cysion.ktbox.base.ITEM_CLICK
import com.cysion.ktbox.net.ErrorStatus
import com.cysion.media.R
import com.cysion.media.adapter.NewsAdapter
import com.cysion.media.constant.BUNDLE_KEY
import com.cysion.media.constant.LINK
import com.cysion.media.constant.TITLE
import com.cysion.media.entity.NewsInfoEntity
import com.cysion.media.presenter.NewsPresenter
import com.cysion.media.ui.activity.NewsDetailActivity
import com.cysion.media.ui.iview.NewsView
import com.cysion.other.startActivity_ex
import com.cysion.uibox.toast.toast
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment(), NewsView {


    override fun getLayoutId(): Int = R.layout.fragment_news
    private var mdatalist: MutableList<NewsInfoEntity> = mutableListOf()
    private val presenter by lazy {
        NewsPresenter().apply { attach(this@NewsFragment) }
    }

    val adapter by lazy {
        NewsAdapter(mdatalist, activity as Context)
    }

    override fun initView() {
        rvNewsList.layoutManager = LinearLayoutManager(activity)
        rvNewsList.adapter = adapter
        adapter.setOnTypeClickListener { obj, position, flag ->
            if (flag == ITEM_CLICK) {
                val bundle = Bundle()
                bundle.putString(TITLE, obj.title)
                bundle.putString(LINK, obj.path)
                context?.startActivity_ex<NewsDetailActivity>(BUNDLE_KEY, bundle)
            }
        }
    }

    override fun lazyLoad() {
        super.lazyLoad()
        presenter.getNewsList()
    }

    override fun visibleAgain() {
        super.visibleAgain()
        if (mdatalist.size == 0) {
            presenter.getNewsList()
        }
    }

    override fun setNewsList(data: MutableList<NewsInfoEntity>) {
        mdatalist.addAll(data)
        adapter.notifyDataSetChanged()
        if (mdatalist.size==0) {
            multiView.showEmpty()
        }
    }

    override fun loading() {
        multiView.showLoading()
    }

    override fun stopLoad() {
        multiView.showContent()
    }

    override fun error(code: Int, msg: String) {
        context.toast(msg)
        if (mdatalist.size == 0) {
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