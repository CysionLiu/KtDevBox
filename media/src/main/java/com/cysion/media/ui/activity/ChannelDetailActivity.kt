package com.cysion.media.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.net.ErrorStatus
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.media.R
import com.cysion.media.adapter.SongAdapter
import com.cysion.media.constant.BUNDLE_KEY
import com.cysion.media.constant.CHANNEL_NAME
import com.cysion.media.constant.TITLE
import com.cysion.media.entity.Song
import com.cysion.media.presenter.SongPresenter
import com.cysion.media.ui.iview.SongView
import com.cysion.other.color
import com.cysion.uibox.bar.TopBar
import com.cysion.uibox.toast.toast
import kotlinx.android.synthetic.main.activity_channel_detail.*

class ChannelDetailActivity : BaseActivity(), SongView {

    private val presenter by lazy {
        SongPresenter().apply {
            attach(this@ChannelDetailActivity)
        }
    }

    private var songList: MutableList<Song> = mutableListOf()

    private val title: String by lazy {
        intent.getBundleExtra(BUNDLE_KEY).getString(TITLE)
    }
    private val channelName: String by lazy {
        intent.getBundleExtra(BUNDLE_KEY).getString(CHANNEL_NAME)
    }

    private val adapter by lazy {
        SongAdapter(songList, self)
    }

    override fun getLayoutId(): Int = R.layout.activity_channel_detail

    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        topbar.apply {
            setTitle(title)
            setOnTopBarClickListener { obj, pos ->
                if (pos == TopBar.Pos.LEFT) {
                    finish()
                }
            }
        }
        rvSongList.layoutManager = LinearLayoutManager(self)
        rvSongList.adapter = adapter
    }

    override fun initData() {
        presenter.getSongs(channelName)
    }

    override fun setSongList(songs: MutableList<Song>) {
        songList.addAll(songs)
        adapter.notifyDataSetChanged()
        if (songList.size == 0) {
            multiView.showEmpty()
        }
    }

    override fun closeMvp() {
        presenter.detach()
    }

    override fun loading() {
        multiView.showLoading()
    }

    override fun stopLoad() {
        multiView.showContent()
    }

    override fun error(code: Int, msg: String) {
        if (songList.size == 0) {
            multiView.showEmpty()
            if (code == ErrorStatus.NETWORK_ERROR) {
                multiView.showNoNetwork()
            }
        }
        toast(msg)
    }
}
