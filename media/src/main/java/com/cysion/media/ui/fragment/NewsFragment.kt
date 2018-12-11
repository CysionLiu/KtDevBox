package com.cysion.media.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import com.cysion.ktbox.base.BaseFragment
import com.cysion.ktbox.base.ITEM_CLICK
import com.cysion.ktbox.utils.logd
import com.cysion.media.R
import com.cysion.media.adapter.NewsAdapter
import com.cysion.media.entity.Data
import com.cysion.media.entity.NewsInfo
import com.cysion.media.presenter.NewsPresenter
import com.cysion.media.ui.activity.NewsDetailActivity
import com.cysion.media.ui.iview.NewsView
import com.cysion.other.startActivity_ex
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment(), NewsView {


    override fun getLayoutId(): Int = R.layout.fragment_news
    private var mdatalist: MutableList<NewsInfo> = mutableListOf()
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
                obj as NewsInfo
                val bundle = Bundle()
                bundle.putString("title", obj.title)
                bundle.putString("link", obj.link)
                context?.startActivity_ex<NewsDetailActivity>("key", bundle)
            }
        }
    }

    override fun lazyLoad() {
        super.lazyLoad()
        presenter.getNewsList()
    }

    override fun setNewsList(data: Data) {
        logd(data.tech.toString())
        mdatalist.addAll(data.tech!!)
        mdatalist.addAll(data.money!!)
        mdatalist.addAll(data.sports!!)
        mdatalist.addAll(data.dy!!)
        adapter.notifyDataSetChanged()
    }

    override fun loading() {
    }

    override fun stopLoad() {
        Button(activity).setOnClickListener(
            { v -> "" }
        )
    }

    override fun error(code: Int, msg: String) {
        logd("$code,$msg")
    }

    override fun closeMvp() {
        presenter.detach()
    }
}