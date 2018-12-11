package com.cysion.media.ui.fragment

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.cysion.ktbox.base.BaseFragment
import com.cysion.media.R
import com.cysion.media.adapter.MusicChnAdapter
import com.cysion.media.entity.ChannelInfo
import com.cysion.media.extension.tos
import com.cysion.media.presenter.ChnPresenter
import com.cysion.media.ui.iview.ChnView
import kotlinx.android.synthetic.main.fragment_music_chn.*

class MusicChannelFragment : BaseFragment(), ChnView {

    private val mChannelList = mutableListOf<ChannelInfo>()

    private val adapter by lazy {
        MusicChnAdapter(mChannelList, context as Context)
    }
    private val presenter by lazy {
        ChnPresenter().apply { attach(this@MusicChannelFragment) }
    }

    override fun getLayoutId(): Int = R.layout.fragment_music_chn

    override fun initView() {
        rvChnList.layoutManager = GridLayoutManager(context, 2)
        rvChnList.adapter = adapter
        adapter.setOnTypeClickListener { obj, position, flag ->
        }
    }

    override fun lazyLoad() {
        super.lazyLoad()
        presenter.getChnList()
    }

    override fun setChannels(datalist: MutableList<ChannelInfo>) {
        mChannelList.clear()
        mChannelList.addAll(datalist)
        adapter.notifyDataSetChanged()
    }

    override fun loading() {
    }

    override fun stopLoad() {
    }

    override fun error(code: Int, msg: String) {
        context?.tos("$code,$msg")
    }

    override fun closeMvp() {
        presenter.detach()
    }
}