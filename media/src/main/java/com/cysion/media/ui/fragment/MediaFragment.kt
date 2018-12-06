package com.cysion.media.ui.fragment

import com.cysion.ktbox.base.BaseFragment
import com.cysion.ktbox.utils.logd
import com.cysion.media.R
import com.cysion.media.entity.Data
import com.cysion.media.presenter.MediaPresenter
import com.cysion.media.ui.iview.MediaView
import kotlinx.android.synthetic.main.fragment_audio.*

class MediaFragment : BaseFragment(), MediaView {

    override fun getLayoutId(): Int = R.layout.fragment_audio
    private val presenter by lazy {
        MediaPresenter().apply { attach(this@MediaFragment) }
    }

    override fun initView() {
        btnGetNews.setOnClickListener {
            presenter.getNewsList()
        }
    }

    override fun setNewsList(data: Data) {
        logd(data.tech.toString())
    }

    override fun loading() {
    }

    override fun stopLoad() {
    }

    override fun error(code: Int, msg: String, event: Int) {
        logd("$code,$msg,$event")
    }

}